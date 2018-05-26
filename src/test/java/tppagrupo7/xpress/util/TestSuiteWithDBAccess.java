package tppagrupo7.xpress.util;

import org.junit.AfterClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithDBAccess {

    private static List<String> tables = new ArrayList<>();

    protected static void destroy() throws SQLException {
        try (Connection connection = getConnection()) {
            tables.stream().map(table -> "DROP TABLE " + table).
                    forEach(sentence -> {
                        Statement statement = null;
                        try{
                            statement = connection.createStatement();
                            statement.executeUpdate(sentence);
                        } catch (SQLException e){
                            throw new RuntimeException("Error.", e);
                        }
                    });
            connection.commit();
        }
    }

    protected static void createTable(String createTableSentence) throws SQLException {
        tables.add(createTableSentence.split(" ")[2]);
        executeSQL(createTableSentence);
    }

    private static void executeSQL(String sentence) throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.executeUpdate(sentence);
            connection.commit();
        }
    }

    protected static void insertRows(String insertRowsSentence) throws SQLException{
        executeSQL(insertRowsSentence);
    }

    protected static Connection getConnection() {
        try{
            return DriverManager.getConnection("jdbc:hsqldb:mem:usuario", "", "");
        } catch (SQLException e ){
            throw new RuntimeException("Can't connect to DB.");
        }

    }

}
