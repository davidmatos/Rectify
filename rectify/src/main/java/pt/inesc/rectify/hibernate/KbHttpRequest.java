package pt.inesc.rectify.hibernate;
// Generated Apr 29, 2017 8:36:51 PM by Hibernate Tools 4.3.1


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
     private String uri;
     private Set kbHttpResponses = new HashSet(0);
     private Set kbDbOps = new HashSet(0);

    public KbHttpRequest() {
    }

	
    public KbHttpRequest(Date ts) {
        this.ts = ts;
    }
    public KbHttpRequest(Date ts, String request, String uri, Set kbHttpResponses, Set kbDbOps) {
       this.ts = ts;
       this.request = request;
       this.uri = uri;
       this.kbHttpResponses = kbHttpResponses;
       this.kbDbOps = kbDbOps;
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
    public Set getKbHttpResponses() {
        return this.kbHttpResponses;
    }
    
    public void setKbHttpResponses(Set kbHttpResponses) {
        this.kbHttpResponses = kbHttpResponses;
    }
    public Set getKbDbOps() {
        return this.kbDbOps;
    }
    
    public void setKbDbOps(Set kbDbOps) {
        this.kbDbOps = kbDbOps;
    }




}


