package pt.inesc.rectify.recover.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import pt.inesc.rectify.Rectify;
import pt.inesc.rectify.utils.HibernateUtil;






/**
 *
 * @author davidmatos
 */
public class DependenciesManager {

    private ArrayList<Relation> relations;

    public DependenciesManager() {

        this.relations = new ArrayList<>();
        fillSchemaRelations();
        
    }

    private void fillSchemaRelations() {
        String query = "SELECT \n"
                + "  `TABLE_SCHEMA`,                          -- Foreign key schema\n"
                + "  `TABLE_NAME`,                            -- Foreign key table\n"
                + "  `COLUMN_NAME`,                           -- Foreign key column\n"
                + "  `REFERENCED_TABLE_SCHEMA`,               -- Origin key schema\n"
                + "  `REFERENCED_TABLE_NAME`,                 -- Origin key table\n"
                + "  `REFERENCED_COLUMN_NAME`                 -- Origin key column\n"
                + "FROM\n"
                + "  `INFORMATION_SCHEMA`.`KEY_COLUMN_USAGE`  -- Will fail if user don't have privilege\n"
                + "WHERE\n"
                + "  `TABLE_SCHEMA` = SCHEMA()                -- Detect current schema in USE \n"
                + "  AND `REFERENCED_TABLE_NAME` IS NOT NULL; ";
        
        
        List<String[]> results = Rectify.hibSession.createSQLQuery(query).list();
        for(String[] result : results){
            
            Relation relation = new Relation(result[0], result[1], result[2], result[3], result[4], result[5]);
            this.relations.add(relation);
        }
    
    
    
    
    }
    
    
    
    
    public ArrayList<Relation> getRelationsByTable(String tableName){
        ArrayList<Relation> result = new ArrayList<>();
        for(Relation relation : this.relations){
            if(relation.getReferencedTableName().equals(tableName) || relation.getTableName().equals(tableName)){
                result.add(relation.clone());    
            }
            
        }
        return result;
                
    }

}
