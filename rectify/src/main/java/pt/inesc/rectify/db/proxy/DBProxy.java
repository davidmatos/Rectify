package pt.inesc.rectify.db.proxy;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import org.slf4j.Logger;
import pt.inesc.rectify.RectifyLogger;

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

    public DBProxy(String remoteHost, int remotePort, int localPort) {
        this.host = remoteHost;
        this.remotePort = remotePort;
        this.localPort = localPort;

    }

    public void stopProxy() throws Exception {

        server.close();

    }

    public void startProxy() throws Exception {
        server = new ServerSocket(localPort);
        
        RectifyLogger.info("DB Proxy for " + host + ":" + remotePort
                + " started on port " + localPort);
        
        while (true) {
            new ThreadProxy(server.accept(), host, remotePort);
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
            final InputStream inFromClient = sClient.getInputStream();
            final OutputStream outToClient = sClient.getOutputStream();
            Socket client = null, server = null;
            // connects a socket to the server
            try {
                server = new Socket(SERVER_URL, SERVER_PORT);
            } catch (IOException e) {
                PrintWriter out = new PrintWriter(new OutputStreamWriter(
                        outToClient));
                out.flush();
                throw new RuntimeException(e);
            }
            // a new thread to manage streams from server to client (DOWNLOAD)
            final InputStream inFromServer = server.getInputStream();
            final OutputStream outToServer = server.getOutputStream();
            // a new thread for uploading to the server
            new Thread() {
                public void run() {
                    int bytes_read;
                    try {
                        while ((bytes_read = inFromClient.read(request)) != -1) {
                            byte[] received;// = new byte[bytes_read];
                            received = Arrays.copyOfRange(request, 4, bytes_read);
                            if (bytes_read - 4 > 1) {
                                if (received[0] == 3 && received[1] == 115) {
                                    String query = new String(received);
                                    //System.out.println("Recebi:" + received[0] +"-" +received[1] +  new String(received));
                                }

                            }

                            outToServer.write(request, 0, bytes_read);
                            outToServer.flush();
                            //TODO CREATE YOUR LOGIC HERE
                        }
                    } catch (IOException e) {
                        RectifyLogger.error(e.getMessage());
                    }
                    try {
                        outToServer.close();
                    } catch (IOException e) {
                        RectifyLogger.error(e.getMessage());
                    }
                }
            }.start();
            // current thread manages streams from server to client (DOWNLOAD)
            int bytes_read;
            try {
                while ((bytes_read = inFromServer.read(reply)) != -1) {
                    outToClient.write(reply, 0, bytes_read);
                    outToClient.flush();
                    //TODO CREATE YOUR LOGIC HERE
                }
            } catch (IOException e) {
                RectifyLogger.error(e.getMessage());
            } finally {
                try {
                    if (server != null) {
                        server.close();
                    }
                    if (client != null) {
                        client.close();
                    }
                } catch (IOException e) {
                    RectifyLogger.error(e.getMessage());
                }
            }
            outToClient.close();
            sClient.close();
        } catch (IOException e) {
            RectifyLogger.error(e.getMessage());
        }
    }
}
