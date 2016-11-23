package pt.inesc.rectify.hibernate;
// Generated Nov 23, 2016 11:54:34 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LogDbRequest generated by hbm2java
 */
public class LogDbRequest  implements java.io.Serializable {


     private Integer id;
     private Date ts;
     private String request;
     private Set logDbResponses = new HashSet(0);

    public LogDbRequest() {
    }

	
    public LogDbRequest(Date ts) {
        this.ts = ts;
    }
    public LogDbRequest(Date ts, String request, Set logDbResponses) {
       this.ts = ts;
       this.request = request;
       this.logDbResponses = logDbResponses;
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
    public Set getLogDbResponses() {
        return this.logDbResponses;
    }
    
    public void setLogDbResponses(Set logDbResponses) {
        this.logDbResponses = logDbResponses;
    }




}


