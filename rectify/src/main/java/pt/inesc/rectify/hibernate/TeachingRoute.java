package pt.inesc.rectify.hibernate;
// Generated May 2, 2017 6:29:09 PM by Hibernate Tools 4.3.1



/**
 * TeachingRoute generated by hbm2java
 */
public class TeachingRoute  implements java.io.Serializable {


     private Integer id;
     private String route;
     private Boolean taught;

    public TeachingRoute() {
    }

    public TeachingRoute(String route, Boolean taught) {
       this.route = route;
       this.taught = taught;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoute() {
        return this.route;
    }
    
    public void setRoute(String route) {
        this.route = route;
    }
    public Boolean getTaught() {
        return this.taught;
    }
    
    public void setTaught(Boolean taught) {
        this.taught = taught;
    }




}


