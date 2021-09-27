package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Database {

    private String databaseName;

    private ArrayList<Table> tables;

    public void addTable(Table table) {
        tables.add(table);
    }
}
