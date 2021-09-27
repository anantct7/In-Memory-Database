package util;

import entities.ColumnInfo;
import entities.Database;
import entities.Table;

import java.util.ArrayList;
import java.util.Optional;

public class DriverCodeUtil {

    public static Database createDatabase(String databaseName) {
        return new Database(databaseName, new ArrayList<>());
    }

    public static Table createTable(String tableName, ArrayList<String> columnNames, ArrayList<ColumnInfo> columnInfos) {
        return new Table(tableName, columnNames, columnInfos);
    }

    public static void addTableToDatabase(Table table, Database database) {
        database.addTable(table);
    }

    public static void insertRecord(Database database, String tableName, ArrayList<Object> record) {
        Optional<Table> tb = getTable(database, tableName);
        if(tb.isPresent()){
            tb.get().insertRecord(record);
        }
    }

    public static ArrayList<ArrayList<Object>> getAllRecords(Database database, String tableName) {
        Optional<Table> tb = getTable(database, tableName);
        if(tb.isPresent()){
            return tb.get().getAllRecords();
        }
        return new ArrayList<>();
    }

    public static ArrayList<ArrayList<Object>> filterOnColumn(Database database, String tableName, String columnName, Object value) {
        Optional<Table> tb = getTable(database, tableName);
        if(tb.isPresent()){
            return tb.get().filterOnColumn(columnName, value);
        }
        return new ArrayList<>();
    }

    private static Optional<Table> getTable(Database database, String tableName) {
        return database.getTables().stream().filter(table -> table.getTableName().equals(tableName)).findFirst();
    }
}
