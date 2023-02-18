package utilities;

public class ReUsableMethods {

    public static String findLocationOfElementInSpecificTableCell(int row, int data){

        String xpath = "//table/tbody/tr[" + row + "]/td[" + data + "]";
        return xpath;

    }

    public static String findLocationOfElementInSpecificTableCell(int table, int row, int data){

        String xpath = "//table[" + table + "]/tbody/tr[" + row + "]/td[" + data + "]";
        return xpath;

    }
}
