package pt.inesc.rectify.recover.database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author davidmatos
 */
public class Relation {

    private String tableSchema;
    private String tableName;
    private String columnName;
    private String referencedTableSchema;
    private String referencedTableName;
    private String referencedTableColumn;

    public Relation(String tableSchema, String tableName, String columnName,
            String referencedTableSchema, String referencedTableName, String referencedTableColumn) {
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.columnName = columnName;
        this.referencedTableSchema = referencedTableSchema;
        this.referencedTableName = referencedTableName;
        this.referencedTableColumn = referencedTableColumn;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getReferencedTableSchema() {
        return referencedTableSchema;
    }

    public void setReferencedTableSchema(String referencedTableSchema) {
        this.referencedTableSchema = referencedTableSchema;
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    public String getReferencedTableColumn() {
        return referencedTableColumn;
    }

    public void setReferencedTableColumn(String referencedTableColumn) {
        this.referencedTableColumn = referencedTableColumn;
    }

    public static void main(String[] args){
        new DependenciesManager();
    }
    
    public Relation clone(){
        Relation relation = new Relation(tableSchema, tableName, columnName, referencedTableSchema, referencedTableName, referencedTableColumn);
        return relation;
    }
    
}
