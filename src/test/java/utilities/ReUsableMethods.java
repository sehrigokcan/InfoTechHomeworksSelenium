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

    //Sütun Değerlerini List içerisine alan method
    public static List<Object> getColumnList(String columnName, String tableName, String query) {

        List<Object> columnData = new ArrayList<>();//ResultSet'ten alınan datanın koyulacağı List.

        //SELECT        id          FROM      students
        //String query = "SELECT " + columnName + " FROM " + tableName;

        executeQuery(query);// => Bu method üstte oluşturuldu. Query'yi çalıştırıp alınan datayı 'resultSet' container'ı içine atama yapıyor.

        try {
            while (resultSet.next()) {
                columnData.add(resultSet.getObject(columnName));//add methodu ile alınan sütun değerlerini List'e ekliyor.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnData;
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
