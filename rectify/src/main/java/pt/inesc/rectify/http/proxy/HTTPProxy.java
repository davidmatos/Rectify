package pt.inesc.rectify.http.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

/**
 *
 * @author David Matos
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.util.Date;

import pt.inesc.rectify.AsyncLogWriter;
import pt.inesc.rectify.Rectify;
import pt.inesc.rectify.RectifyLogger;
import pt.inesc.rectify.hibernate.KbHttpRequest;

/**
 * Hello world!
 *
 */
public class HTTPProxy {

    private String remoteAddress = "";
    private HttpProxyServer server;
    private int localPort;

    public HTTPProxy(String proxiedUrl, int localPort) {
        this.remoteAddress = proxiedUrl;
        this.localPort = localPort;
    }

    public void startProxy() {
        server = DefaultHttpProxyServer.bootstrap().withPort(this.localPort).withFiltersSource(new RectifyHTTPFilter())
                .start();
        RectifyLogger.info("HTTP Proxy for " + this.remoteAddress + " started on port " + this.localPort);

    }

    public void stopProxy() {
        server.stop();
        RectifyLogger.info("HTTP Proxy for " + this.remoteAddress + " stoped");
    }

    class RectifyHTTPFilter extends HttpFiltersSourceAdapter {

        @Override
        public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
            return new HttpFiltersAdapter(originalRequest) {
                @Override
                public HttpResponse clientToProxyRequest(HttpObject httpObject) {

                    if (originalRequest.getUri().contains("favicon")) {
                        return null;
                    }

//                    AsyncLogWriter.getInstance().addLogHttpRequest(originalRequest.toString(), originalRequest.getUri());
                    originalRequest.setUri(remoteAddress + originalRequest.getUri());

                    if (Rectify.getInstance().isInTeachingMode()) {
                        // Training mode. Should store every
                        // request in the KB
                        if (Rectify.getInstance().getCurrentKbHttpRequest() == null) {
                            Rectify.getInstance().setCurrentKbHttpRequest(new KbHttpRequest(new Date(), originalRequest.toString(), originalRequest.getUri(), null));

                        }
                    } else {
                        // Normal mode. Should store every
                        // request in the DB Log

                        AsyncLogWriter.getInstance().addLogHttpRequest(originalRequest.toString(),
                                originalRequest.getUri(), "host");

                    }

                    URL obj = null;
                    try {
                        obj = new URL(originalRequest.getUri());
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }

                    HttpURLConnection con = null;
                    try {
                        con = (HttpURLConnection) obj.openConnection();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    // optional default is GET
                    try {
                        con.setRequestMethod(originalRequest.getMethod().name());
                    } catch (ProtocolException e1) {
                        e1.printStackTrace();
                    }

                    // add request header
                    // con.setRequestProperty("User-Agent", originalRequest.g);
                    int responseCode = 0;
                    try {
                        responseCode = con.getResponseCode();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    BufferedReader in = null;
                    InputStreamReader isr = null;
                    try {
                        isr = new InputStreamReader(con.getInputStream());

                        in = new BufferedReader(isr);

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    try {
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    ByteBuf buffer = null;
                    try {
                        buffer = Unpooled.wrappedBuffer(response.toString()
                                .replaceAll(remoteAddress, "http://localhost:" + localPort).getBytes("UTF-8"));
//                         buffer = Unpooled.wrappedBuffer( response.toString().getBytes("UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                            buffer);
                    HttpHeaders.setContentLength(httpResponse, buffer.readableBytes());

                    HttpHeaders.setHeader(httpResponse, HttpHeaders.Names.CONTENT_TYPE,
                            con.getHeaderField(HttpHeaders.Names.CONTENT_TYPE));

                    if (Rectify.getInstance().isInTeachingMode()) {
                        // Training mode. Should store every
                        // request in the KB
//                        if (Rectify.getInstance().getCurrentKbHttpResponse() != null) {
//                            // Rectify.currentKbHttpRequest.setKbDbOps(Rectify.currentKbDbOps);
//                            Set<KbHttpResponse> setKbHttpResponses = new HashSet<KbHttpResponse>();
//                            setKbHttpResponses.add(new KbHttpResponse(Rectify.getInstance().getCurrentKbHttpRequest(), new Date()));
//                            Rectify.getInstance().getCurrentKbHttpRequest().setKbHttpResponses(setKbHttpResponses);
//                        }
                    } else {

//                        AsyncLogWriter.getInstance().addLogHttpRequest(originalRequest.toString(),
//                                originalRequest.getUri(), , ctx.channel().localAddress().toString());
                    }

                    return httpResponse;
                }

                @Override
                public HttpObject serverToProxyResponse(HttpObject httpObject) {
                    return httpObject;
                }
            };
        }

    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public HttpProxyServer getServer() {
        return server;
    }

    public void setServer(HttpProxyServer server) {
        this.server = server;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }


}
