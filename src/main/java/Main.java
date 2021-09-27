import entities.*;
import util.DriverCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Database database = DriverCodeUtil.createDatabase("sample_database");
        Table table = DriverCodeUtil.createTable(
                "sample_table",
                new ArrayList<String>(
                        Arrays.asList(
                                "id",
                                "rollNo",
                                "name",
                                "age"
                        )),
                new ArrayList<ColumnInfo>(
                        Arrays.asList(
                                new ColumnInfo(DataType.INTEGER, Constraint.REQUIRED),
                                new ColumnInfo(DataType.STRING, Constraint.REQUIRED),
                                new ColumnInfo(DataType.STRING, Constraint.REQUIRED),
                                new ColumnInfo(DataType.INTEGER, Constraint.NOT_REQUIRED)
                        ))
                );
        DriverCodeUtil.addTableToDatabase(table, database);
        ArrayList<Object> record1 = new ArrayList<>(Arrays.asList(123, "45", "Anant", 23));
        ArrayList<Object> record2 = new ArrayList<>(Arrays.asList(124, "56"));
        DriverCodeUtil.insertRecord(database, "sample_table", record1);
        DriverCodeUtil.insertRecord(database, "sample_table", record2);
        ArrayList<ArrayList<Object>> result = DriverCodeUtil.getAllRecords(database, "sample_table");
        for(int i=0;i<result.size();i++) {
            System.out.println(result.get(i));
        }
        ArrayList<ArrayList<Object>> filtered = DriverCodeUtil.filterOnColumn(database, "sample_table", "id", 123);
        for(int i=0;i<filtered.size();i++) {
            System.out.println(filtered.get(i));
        }
    }
}
