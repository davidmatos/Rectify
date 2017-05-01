package pt.inesc.rectify.hibernate;
// Generated May 1, 2017 3:05:02 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LogDbStatement generated by hbm2java
 */
public class LogDbStatement  implements java.io.Serializable {


     private Integer id;
     private Date ts;
     private String request;

    public LogDbStatement() {
    }

	
    public LogDbStatement(Date ts) {
        this.ts = ts;
    }
    public LogDbStatement(Date ts, String request) {
       this.ts = ts;
       this.request = request;
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




}


