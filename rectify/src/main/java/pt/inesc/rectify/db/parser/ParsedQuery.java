package pt.inesc.rectify.db.parser;



import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author davidmatos
 */
public class ParsedQuery {
    
    public String[] KEYWORDS = new String[]{"SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "CREATE", "INTO", "INNERJOIN", "LEFTOUTERJOIN", "RIGHTOUTERJOIN", "GROUPBY", "ORDERBY"};
    
    public String[][] keywordsWithSpaces = new String[][]{{"INNER JOIN", "INNERJOIN"}, {"LEFT OUTER JOIN", "LEFTOUTERJOIN"}, {"RIGHT OUTER JOIN", "RIGHTOUTERJOIN"}, {"GROUP BY", "GROUPBY"}, {"ORDER BY", "ORDERBY"}};
    
    
    
    private String query;
    private String queryType;
    private ArrayList<String> columns;
    private ArrayList<String> tables;
    private String whereClause;
    private String orderByClause;
    private String groupByClause;
    private Node rootNode; 
    private Node currentNode;
    
    public ParsedQuery(String query){
        this.query = query.toUpperCase();
        this.rootNode = null;
        this.columns = new ArrayList<>();
        this.tables = new ArrayList<>();
        
        for(String[] keywordWithSpaces : keywordsWithSpaces){
            query.replaceAll(keywordWithSpaces[0], keywordWithSpaces[1]);
        }
        
        
        
    }
    
    public void parse(){
        
        
        
        
        
        boolean columnsPart = false;
        
        String[] queryWords = query.replaceAll("\n", " ").replaceAll(",", " ").replaceAll("  ", " ").replaceAll("  ", " ").split(" ");
        for(int i = 0; i < queryWords.length; i++){
            for(int j = 0; j < KEYWORDS.length; j++){
            if(KEYWORDS[j].equals(queryWords[i])){
                columnsPart = false;
                if(this.rootNode == null){
                    this.rootNode = new Node(KEYWORDS[j]);
                    this.currentNode = this.rootNode;
                    this.queryType = KEYWORDS[j];
                    if(KEYWORDS[j].equals("SELECT") || KEYWORDS[j].equals("INTO") ){
                        columnsPart = true;
                    }
                    
                }else{
                    Node newNode = new Node(KEYWORDS[j]);
                    this.currentNode.addChildNode(newNode);
                    this.currentNode = newNode;
                    
                }
                
            }else{
                this.currentNode.addChild(queryWords[i]);
                if(columnsPart){
                        this.columns.add(queryWords[i]);
                    }
                
            }
            }
            
        }
    }
    
    
    private class Node{
        private String keyword;
        private ArrayList<String> childreen;
        private Node childNode;
        
        public Node(String keyword){
            this.keyword = keyword;
            this.childreen = new ArrayList<>();
        }
        
        public void addChild(String child){
            this.childreen.add(child);
        }
        
        
        public void addChildNode(Node node){
            this.childNode = node;
        }
    }
    
}
