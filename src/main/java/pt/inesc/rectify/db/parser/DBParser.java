/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.inesc.rectify.db.parser;

import java.util.Date;
import pt.inesc.rectify.hibernate.KbDbStatement;
import pt.inesc.rectify.hibernate.KbHttpRequest;

/**
 *
 * @author davidmatos
 */
public class DBParser {

    public static KbDbStatement getKbDbStatement(String query) {
        return getKbDbStatement(query, null);
    }
    
    public static KbDbStatement getKbDbStatement(String query, KbHttpRequest kbHttpRequest) {
        ParsedQuery parsedQuery = new ParsedQuery(query);
        Date ts = new Date();
        String statementType = parsedQuery.getStatementType();
        Integer nrOfColumns = parsedQuery.getColumns().size();
        String columns = parsedQuery.getColumns().toString();
        String columnsValues = parsedQuery.getValues().toString();
        String tables = parsedQuery.getTables().toString();
        String whereClause = parsedQuery.getWhereColumns().toString();
        String whereClauseValues = parsedQuery.getWhereValues().toString();

        KbDbStatement kbDbStatement = new KbDbStatement(null,
                ts,
                 query, statementType,
                 nrOfColumns, columns,
                 columnsValues, tables,
                 whereClause, whereClauseValues
        );

        return kbDbStatement;
    }
}
