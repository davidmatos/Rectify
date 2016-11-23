package pt.inesc.rectify.hibernate;
// Generated Nov 23, 2016 11:54:34 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * KbHttpRequest generated by hbm2java
 */
public class KbHttpRequest  implements java.io.Serializable {


     private Integer id;
     private Date ts;
     private String request;
     private Set kbHttpRequestPartses = new HashSet(0);
     private Set kbHttpResponses = new HashSet(0);

    public KbHttpRequest() {
    }

	
    public KbHttpRequest(Date ts) {
        this.ts = ts;
    }
    public KbHttpRequest(Date ts, String request, Set kbHttpRequestPartses, Set kbHttpResponses) {
       this.ts = ts;
       this.request = request;
       this.kbHttpRequestPartses = kbHttpRequestPartses;
       this.kbHttpResponses = kbHttpResponses;
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
    public Set getKbHttpRequestPartses() {
        return this.kbHttpRequestPartses;
    }
    
    public void setKbHttpRequestPartses(Set kbHttpRequestPartses) {
        this.kbHttpRequestPartses = kbHttpRequestPartses;
    }
    public Set getKbHttpResponses() {
        return this.kbHttpResponses;
    }
    
    public void setKbHttpResponses(Set kbHttpResponses) {
        this.kbHttpResponses = kbHttpResponses;
    }




}


