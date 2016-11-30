package pt.inesc.rectify.hibernate;
// Generated Nov 29, 2016 11:21:05 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Logs generated by hbm2java
 */
public class Logs  implements java.io.Serializable {


     private Integer id;
     private String userId;
     private Date dated;
     private String logger;
     private String level;
     private String message;

    public Logs() {
    }

    public Logs(String userId, Date dated, String logger, String level, String message) {
       this.userId = userId;
       this.dated = dated;
       this.logger = logger;
       this.level = level;
       this.message = message;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getDated() {
        return this.dated;
    }
    
    public void setDated(Date dated) {
        this.dated = dated;
    }
    public String getLogger() {
        return this.logger;
    }
    
    public void setLogger(String logger) {
        this.logger = logger;
    }
    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }




}


