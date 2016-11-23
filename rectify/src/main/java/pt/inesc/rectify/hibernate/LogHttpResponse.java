package pt.inesc.rectify.hibernate;
// Generated Nov 23, 2016 11:54:34 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LogHttpResponse generated by hbm2java
 */
public class LogHttpResponse  implements java.io.Serializable {


     private Integer id;
     private LogHttpRequest logHttpRequest;
     private Date ts;
     private String response;

    public LogHttpResponse() {
    }

	
    public LogHttpResponse(LogHttpRequest logHttpRequest, Date ts) {
        this.logHttpRequest = logHttpRequest;
        this.ts = ts;
    }
    public LogHttpResponse(LogHttpRequest logHttpRequest, Date ts, String response) {
       this.logHttpRequest = logHttpRequest;
       this.ts = ts;
       this.response = response;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public LogHttpRequest getLogHttpRequest() {
        return this.logHttpRequest;
    }
    
    public void setLogHttpRequest(LogHttpRequest logHttpRequest) {
        this.logHttpRequest = logHttpRequest;
    }
    public Date getTs() {
        return this.ts;
    }
    
    public void setTs(Date ts) {
        this.ts = ts;
    }
    public String getResponse() {
        return this.response;
    }
    
    public void setResponse(String response) {
        this.response = response;
    }




}


