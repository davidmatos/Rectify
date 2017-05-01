package pt.inesc.rectify.db.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


import pt.inesc.rectify.AsyncLogWriter;

import pt.inesc.rectify.Rectify;
import pt.inesc.rectify.RectifyLogger;
import pt.inesc.rectify.db.parser.DBParser;
import pt.inesc.rectify.hibernate.KbDbStatement;


/**
 *
 * @author David Matos
 *
 */
public class DBProxy {

    private String host = "localhost";
    private int remotePort = 3306;
    private int localPort = 6789;

    private ServerSocket server;

    private ServerThread serverThread;

    public DBProxy(String remoteHost, int remotePort, int localPort) {
        this.host = remoteHost;
        this.remotePort = remotePort;
        this.localPort = localPort;

    }

    public void stopProxy() throws Exception {

        server.close();
        
        RectifyLogger.info("DB Proxy for " + host + ":" + remotePort + " stoped");

    }

    public void startProxy() throws Exception {
        server = new ServerSocket(localPort);

        RectifyLogger.info("DB Proxy for " + host + ":" + remotePort + " started on port " + localPort);
        this.serverThread = new ServerThread(server, host, remotePort);
        this.serverThread.start();

    }

}

// waits for new connections
class ServerThread extends Thread {

    private ServerSocket ss;
    private String host;
    private int remotePort;

    public ServerThread(ServerSocket ss, String host, int remotePort) {
        this.ss = ss;
        this.host = host;
        this.remotePort = remotePort;

    }

    @Override
    public void run() {

        while (true) {
            try {

                new ThreadProxy(ss.accept(), host, remotePort);
            } catch (Exception e) {

            }

        }
    }

}

/**
 * Handles a socket connection to the proxy server from the client and uses 2
 * threads to proxy between server and client
 *
 * @author David Matos
 *
 */
class ThreadProxy extends Thread {

    private Socket sClient;
    private final String SERVER_URL;
    private final int SERVER_PORT;

    private Socket client = null, server = null;
    private InputStream inFromClient = null;
    private OutputStream outToClient = null;

    private InputStream inFromServer = null;
    private OutputStream outToServer = null;

    ThreadProxy(Socket sClient, String ServerUrl, int ServerPort) {
        this.SERVER_URL = ServerUrl;
        this.SERVER_PORT = ServerPort;
        this.sClient = sClient;
        this.start();
    }

    @Override
    public void run() {
        try {
            final byte[] request = new byte[1024];
            byte[] reply = new byte[4096];
            inFromClient = sClient.getInputStream();
            outToClient = sClient.getOutputStream();

            restartServerSocket();
            // a new thread for uploading to the server
            new Thread() {
                public void run() {
                    int bytes_read;
                    try {
                        while ((bytes_read = inFromClient.read(request)) != -1) {
                            if (bytes_read > 10) {

                                byte[] received = Arrays.copyOfRange(request, 5, bytes_read);
                                String query = new String(received);

//								 System.out.println("query1:" + query);
//								 System.out.println("["+request[4]+"]query2:"
//								 + new String(Arrays.copyOfRange(request, 5,
//								 bytes_read)));
                                if (request[4] == 3) {

//									RectifyLogger.info(query);
                                    if (Rectify.getInstance().isInTeachingMode()) {
                                        // Training mode. Should store every
                                        // request in the KB
                                        if (Rectify.getInstance().getCurrentKbHttpRequest() != null) {
                                            
                                            KbDbStatement dbStmt = DBParser.getKbDbStatement(query);
                                            Rectify.getInstance().addCurrentKbDbStatement(dbStmt);
                                        }
                                    } else {
                                        // Normal mode. Should store every
                                        // request in the DB Log
                                        System.out.println("Logging this query:"+query);
                                        AsyncLogWriter.getInstance().addLogDbStatement(query);
                                    }

                                }

                            }
                            outToServer.write(request, 0, bytes_read);
                            outToServer.flush();
                            // TODO CREATE YOUR LOGIC HERE
                        }
                    } catch (IOException e) {
                        RectifyLogger.severe("[101]" + e.getMessage());
                    }
                    try {
                        outToServer.close();
                    } catch (IOException e) {
                        RectifyLogger.severe("[102]" + e.getMessage());
                    }
                }
            }.start();
            // current thread manages streams from server to client (DOWNLOAD)
            int bytes_read;
            try {
                if (server.isClosed()) {
                    restartServerSocket();
                }

                while ((bytes_read = inFromServer.read(reply)) != -1) {
                    outToClient.write(reply, 0, bytes_read);
                    outToClient.flush();
                    // TODO CREATE YOUR LOGIC HERE
                }
            } catch (IOException e) {
                RectifyLogger.severe("[103]" + e.getMessage());
            } finally {
                try {
                    if (server != null) {
                        server.close();
                    }
                    if (client != null) {
                        client.close();
                    }
                } catch (IOException e) {
                    RectifyLogger.severe("[104]" + e.getMessage());
                }
            }
            outToClient.close();
            sClient.close();
        } catch (IOException e) {
            RectifyLogger.severe("[105]" + e.getMessage());
        }
    }

    private void restartServerSocket() {
        try {
            server = new Socket(SERVER_URL, SERVER_PORT);

            // a new thread to manage streams from server to client (DOWNLOAD)
            inFromServer = server.getInputStream();
            outToServer = server.getOutputStream();
        } catch (IOException e) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outToClient));
            out.flush();
            // throw new RuntimeException(e);
            RectifyLogger.severe("[106]" + e.getMessage());
        }

    }

}
