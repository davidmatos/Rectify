package pt.inesc.rectify.http.proxy;

/**
 *
 * @author David Matos
 */
import com.mysql.jdbc.Driver;
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

/**
 * Hello world!
 *
 */
public class HTTPProxy {

    private String proxiedUrl = "https://pplware.sapo.pt";
    private HttpProxyServer server;
    
    
    Driver driver;
    
    public HTTPProxy(String proxiedUrl){
        this.proxiedUrl = proxiedUrl;
    }
    
    
    public void startProxy(){
           server
                = DefaultHttpProxyServer.bootstrap()
                        .withPort(8080)
                        .withFiltersSource(new HttpFiltersSourceAdapter() {
                            public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
                                return new HttpFiltersAdapter(originalRequest) {
                                    @Override
                                    public HttpResponse clientToProxyRequest(HttpObject httpObject) {

                                        originalRequest.setUri(proxiedUrl + originalRequest.getUri());

                                        System.out.println("Request to:" + originalRequest.getUri());

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

                                        //add request header
                                        //con.setRequestProperty("User-Agent", originalRequest.g);
                                        int responseCode = 0;
                                        try {
                                            responseCode = con.getResponseCode();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                        System.out.println("\nSending 'GET' request to URL : " + originalRequest.getUri());
                                        System.out.println("Response Code : " + responseCode);

                                        BufferedReader in = null;
                                        try {
                                            in = new BufferedReader(
                                                    new InputStreamReader(con.getInputStream()));
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
                                            buffer = Unpooled.wrappedBuffer(response.toString().replaceAll(proxiedUrl, "localhost:8080").getBytes("UTF-8"));
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);
                                        HttpHeaders.setContentLength(httpResponse, buffer.readableBytes());
                                        HttpHeaders.setHeader(httpResponse, HttpHeaders.Names.CONTENT_TYPE, "text/html");
                                        return httpResponse;
                                    }

                                    @Override
                                    public HttpObject serverToProxyResponse(HttpObject httpObject) {
                                        System.out.println(httpObject.toString());
                                        return httpObject;
                                    }
                                };
                            }
                        })
                        .start();

    }
    
    
    public void stopProxy(){
        server.stop();
    }
    
      
}
