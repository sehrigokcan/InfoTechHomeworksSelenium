package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReUsableMethods {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public static String findLocationOfElementInSpecificTableCell(int row, int data){

        String xpath = "//table/tbody/tr[" + row + "]/td[" + data + "]";
        return xpath;

    }

    public static String findLocationOfElementInSpecificTableCell(int table, int row, int data){

        String xpath = "//table[" + table + "]/tbody/tr[" + row + "]/td[" + data + "]";
        return xpath;

    }


    //ExecuteQuery methodu
    public static ResultSet executeQuery(String query) {

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }
}
