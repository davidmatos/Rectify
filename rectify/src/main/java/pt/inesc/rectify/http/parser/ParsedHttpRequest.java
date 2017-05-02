/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify.http.parser;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author davidmatos
 */
public class ParsedHttpRequest {

    private String request;

    private String method;
    private String host;
    private String uri;
    private Date ts;

    private ArrayList<String> parameters;
    private ArrayList<String> values;

    public ParsedHttpRequest(String request, Date ts) {
        this.ts = ts;
        this.request = request;
        this.method = HttpParser.getMethod(request);
        this.host = HttpParser.getHost(request);
        this.uri = HttpParser.getUri(request);
    }

    public ParsedHttpRequest(String request) {
        this.request = request;

        this.method = HttpParser.getMethod(request);
        this.host = HttpParser.getHost(request);
        this.uri = HttpParser.getUri(request);
        this.ts = new Date();
        if (this.method.equalsIgnoreCase("GET")) {
            this.parameters = HttpParser.getGetParameters(request);
            this.values = HttpParser.getGetValues(request);
        }
    }

    public ParsedHttpRequest(String method, String host, String uri, Date ts) {
        this.method = method;
        this.host = host;
        this.uri = uri;
        this.ts = ts;
        if (this.method.equalsIgnoreCase("GET")) {
            this.parameters = HttpParser.getGetParameters(request);
            this.values = HttpParser.getGetValues(request);
        }

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

}
