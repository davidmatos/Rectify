package pt.inesc.rectify.hibernate;
// Generated May 2, 2017 6:29:09 PM by Hibernate Tools 4.3.1



/**
 * Configuration generated by hbm2java
 */
public class Configuration  implements java.io.Serializable {


     private Integer id;
     private String configurationName;
     private String configurationValue;

    public Configuration() {
    }

    public Configuration(String configurationName, String configurationValue) {
       this.configurationName = configurationName;
       this.configurationValue = configurationValue;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getConfigurationName() {
        return this.configurationName;
    }
    
    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }
    public String getConfigurationValue() {
        return this.configurationValue;
    }
    
    public void setConfigurationValue(String configurationValue) {
        this.configurationValue = configurationValue;
    }




}

