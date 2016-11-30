package pt.inesc.rectify.hibernate;
// Generated Nov 29, 2016 11:21:05 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LogHttpRequest generated by hbm2java
 */
public class LogHttpRequest  implements java.io.Serializable {


     private Integer id;
     private Date ts;
     private String request;
     private Set<LogHttpResponse> logHttpResponses = new HashSet<LogHttpResponse>(0);

    public LogHttpRequest() {
    }

	
    public LogHttpRequest(Date ts) {
        this.ts = ts;
    }
    public LogHttpRequest(Date ts, String request, Set<LogHttpResponse> logHttpResponses) {
       this.ts = ts;
       this.request = request;
       this.logHttpResponses = logHttpResponses;
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
    public Set<LogHttpResponse> getLogHttpResponses() {
        return this.logHttpResponses;
    }
    
    public void setLogHttpResponses(Set<LogHttpResponse> logHttpResponses) {
        this.logHttpResponses = logHttpResponses;
    }




}


