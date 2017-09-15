package pt.inesc.rectify.db.parser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author davidmatos
 */
public class ParsedQuery {

    private static String[] STATEMENT_TYPES = new String[]{"SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "CREATE", "ALTER", "RENAME TABLE", "SET"};

    private static String[][] keywordsWithSpaces = new String[][]{{"INNER JOIN", "INNERJOIN"}, {"LEFT OUTER JOIN", "LEFTOUTERJOIN"}, {"RIGHT OUTER JOIN", "RIGHTOUTERJOIN"}, {"GROUP BY", "GROUPBY"}, {"ORDER BY", "ORDERBY"}};

    private String statement;
    private String statementType;
    private ArrayList<String> columns;
    private ArrayList<String> tables;
    private ArrayList<String> values;
    private ArrayList<String> whereColumns;
    private ArrayList<String> whereValues;

    public ParsedQuery(String query) {
        this.statement = query.toUpperCase();

        this.statementType = "";
        this.columns = new ArrayList<>();
        this.values = new ArrayList<>();
        this.tables = new ArrayList<>();

        this.whereColumns = new ArrayList<>();
        this.whereValues = new ArrayList<>();

        parse();

    }

    private void parse() {

        //Get the statementType
        for (int i = 0; i < STATEMENT_TYPES.length; i++) {
            if (this.statement.startsWith(STATEMENT_TYPES[i])) {
                this.statementType = STATEMENT_TYPES[i];
            }
        }

        //
        //
        // Get the columns
        //
        //
        String columnsStr = "";
        if (this.statementType.equals("SELECT") && this.statement.indexOf("FROM") > 0) {

            columnsStr = this.statement.substring(7, this.statement.indexOf("FROM"));
        }
        if (this.statementType.equals("UPDATE")) {
            if (this.statement.contains("WHERE")) {
                columnsStr = this.statement.substring(this.statement.indexOf("SET") + 4, this.statement.indexOf("WHERE"));
            } else {
                columnsStr = this.statement.substring(this.statement.indexOf("SET") + 4);
            }
            String[] columnsStrArray = columnsStr.split(",");
            columnsStr = "";
            for (String columnsStrElem : columnsStrArray) {
                columnsStr = columnsStrElem.split("=")[0];
            }
        }
        if (this.statementType.equals("INSERT")) {
            columnsStr = this.statement.substring(this.statement.indexOf("(") + 1, this.statement.indexOf(")"));
            columnsStr = columnsStr.replaceAll("`", "");
        }
        columnsStr = columnsStr.trim();
        this.columns.addAll(Arrays.asList(columnsStr.split(",")));
        //Limpeza
        for (int i = 0; i < this.columns.size(); i++) {
            if (this.columns.get(i).equals("")) {
                this.columns.remove(i);
                i--;
                continue;
            }
            this.columns.set(i, this.columns.get(i).trim());
        }

        //
        //
        //Get the tables
        //
        //
        String tablesString = "";
        if (this.statementType.equals("SELECT") && this.statement.indexOf(" WHERE") > 0) {
            String temp = this.statement.substring(this.statement.indexOf("FROM") + 5, this.statement.indexOf(" WHERE"));
            String[] parts = temp.split(" JOIN ");
            for (String part : parts) {
                tablesString += part.replaceAll("RIGHT OUTER", "").replaceAll("LEFT OUTER", "").replaceAll("INNER", "").replaceAll("OUTER", "");
            }

        }
        if (this.statementType.equals("UPDATE")) {
            tablesString = this.statement.substring(this.statement.indexOf("UPDATE") + 7, this.statement.indexOf("SET"));
        }
        if (this.statementType.equals("INSERT")) {
            tablesString = this.statement.substring(this.statement.indexOf("INTO") + 5, this.statement.indexOf("(") - 1);
        }
        if (this.statementType.equals("DELETE")) {
            tablesString = this.statement.substring(this.statement.indexOf("FROM") + 5, this.statement.indexOf("WHERE") - 1);

        }
        this.tables.addAll(Arrays.asList(tablesString.split(" ")));
        //Limpeza
        for (int i = 0; i < this.tables.size(); i++) {
            if (this.tables.get(i).equals("")) {
                this.tables.remove(i);
                i--;
                continue;
            }
            this.tables.set(i, this.tables.get(i).trim());
        }

        //
        //
        //WHERE clause
        //
        //
        if (this.statement.contains(" WHERE ")) {
            String whereClause = this.statement.split(" WHERE ")[1];
            if (whereClause.contains(" ORDER")) {
                whereClause = whereClause.substring(0, whereClause.indexOf(" ORDER"));
            }

            if (whereClause.contains(" GROUP")) {
                whereClause = whereClause.substring(0, whereClause.indexOf(" GROUP"));
            }

            if (whereClause.contains(" LIMIT")) {
                whereClause = whereClause.substring(0, whereClause.indexOf(" LIMIT"));
            }

            whereClause = whereClause.replaceAll(" OR ", " AND ");

            String[] whereClauseParts = whereClause.split(" AND ");
            for (String whereClausePart : whereClauseParts) {

                whereClausePart = whereClausePart.replaceAll("<>", "=").replaceAll("!=", "=").replaceAll("<", "=").replaceAll(">", "=").replaceAll("IN", "=");

                if(whereClausePart.contains("=")){
                    String column = whereClausePart.split("=")[0];
                String value = whereClausePart.split("=")[1];
                this.whereColumns.add(column.trim());
                this.whereValues.add(value.trim().replaceAll("'", ""));
                }
                
            }
        }

        //
        //
        // Get the values
        //
        //
        String valuesString = "";
        if (this.statementType.equals("UPDATE")) {
            if (this.statement.contains("WHERE")) {
                valuesString = this.statement.substring(this.statement.indexOf("SET") + 4, this.statement.indexOf("WHERE"));
            } else {
                valuesString = this.statement.substring(this.statement.indexOf("SET") + 4);
            }
            String[] columnsStrArray = valuesString.split(",");
            valuesString = "";
            for (String columnsStrElem : columnsStrArray) {
                valuesString = columnsStrElem.split("=")[1];
            }
        }

        if (this.statementType.equals(
                "INSERT")) {
            valuesString = this.statement.substring(this.statement.indexOf("VALUES"));
            valuesString = valuesString.substring(valuesString.indexOf("(") + 1, valuesString.indexOf(")"));
            
        }
        valuesString = valuesString.trim();

        this.values.addAll(Arrays.asList(valuesString.split(" ")));
        //Limpeza
        for (int i = 0; i < this.values.size(); i++) {
            if (this.values.get(i).equals("") || this.values.get(i).equals(" ")) {
                this.values.remove(i);
                i--;
                continue;
            }
            this.values.set(i, this.values.get(i).trim());
            this.values.set(i, this.values.get(i).replaceAll("'", ""));
            this.values.set(i, this.values.get(i).replaceAll(",", ""));
        }

        
        
        
        
    }

    public String[][] getKeywordsWithSpaces() {
        return keywordsWithSpaces;
    }

    public void setKeywordsWithSpaces(String[][] keywordsWithSpaces) {
        this.keywordsWithSpaces = keywordsWithSpaces;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    public ArrayList<String> getTables() {
        return tables;
    }

    public void setTables(ArrayList<String> tables) {
        this.tables = tables;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public ArrayList<String> getWhereColumns() {
        return whereColumns;
    }

    public void setWhereColumns(ArrayList<String> whereColumns) {
        this.whereColumns = whereColumns;
    }

    public ArrayList<String> getWhereValues() {
        return whereValues;
    }

    public void setWhereValues(ArrayList<String> whereValues) {
        this.whereValues = whereValues;
    }

    public boolean isDisposable() {
        return this.columns.size() == 0;
    }

}
