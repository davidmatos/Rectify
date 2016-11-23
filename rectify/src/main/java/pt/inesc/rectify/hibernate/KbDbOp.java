package pt.inesc.rectify.hibernate;
// Generated Nov 23, 2016 11:54:34 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * KbDbOp generated by hbm2java
 */
public class KbDbOp  implements java.io.Serializable {


     private Integer id;
     private Date ts;
     private String query;
     private Set kbDbOpPartses = new HashSet(0);

    public KbDbOp() {
    }

	
    public KbDbOp(Date ts) {
        this.ts = ts;
    }
    public KbDbOp(Date ts, String query, Set kbDbOpPartses) {
       this.ts = ts;
       this.query = query;
       this.kbDbOpPartses = kbDbOpPartses;
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
    public String getQuery() {
        return this.query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    public Set getKbDbOpPartses() {
        return this.kbDbOpPartses;
    }
    
    public void setKbDbOpPartses(Set kbDbOpPartses) {
        this.kbDbOpPartses = kbDbOpPartses;
    }




}

