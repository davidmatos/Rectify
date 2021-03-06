package pt.inesc.rectify.hibernate;
// Generated May 2, 2017 6:29:09 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LogHttpRequest generated by hbm2java
 */
public class LogHttpRequest  implements java.io.Serializable {


     private Integer id;
     private Date ts;
     private String request;
     private String uri;
     private String host;
     private Integer i;

    public LogHttpRequest() {
    }

	
    public LogHttpRequest(Date ts) {
        this.ts = ts;
    }
    public LogHttpRequest(Date ts, String request, String uri, String host, Integer i) {
       this.ts = ts;
       this.request = request;
       this.uri = uri;
       this.host = host;
       this.i = i;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getTs() {
        return this.ts;
    }
    
    public void setTs(Date ts) {
        this.ts = ts;
    }
    public String getRequest() {
        return this.request;
    }
    
    public void setRequest(String request) {
        this.request = request;
    }
    public String getUri() {
        return this.uri;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getHost() {
        return this.host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    public Integer getI() {
        return this.i;
    }
    
    public void setI(Integer i) {
        this.i = i;
    }




}


