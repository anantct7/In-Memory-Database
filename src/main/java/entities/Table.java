package entities;

import lombok.Data;
import validation.IntValidation;
import validation.RequiredValidation;
import validation.StringValidation;
import validation.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Data
public class Table {

    private String tableName;

    private ArrayList<String> columnNames;

    private ArrayList<ColumnInfo> columnInfos;

    private ArrayList<ArrayList<Validation>> columnValidations;

    private ArrayList<ArrayList<Object>> table;

    public Table(String tableName, ArrayList<String> columnNames, ArrayList<ColumnInfo> columnInfos) {
        this.table = new ArrayList<ArrayList<Object>>();
        this.tableName = tableName;
        this.columnNames = columnNames;
        this.columnInfos = columnInfos;
        this.columnValidations = new ArrayList<>();
        for(ColumnInfo columnInfo: columnInfos) {
            columnValidations.add(
                    new ArrayList<>(Arrays.asList(
                            getDataTypeValidation(columnInfo.getDataType()),
                            getConstraintValidation(columnInfo.getConstraint())))
            );
        }
    }

    public void insertRecord(ArrayList<Object> record) {
        boolean isInvalidRecord = false;
        for(int i=0;i<columnNames.size(); i++) {
            if(!isValidEntry(i, ((i>=record.size())?null:record.get(i)))){
                isInvalidRecord = true;
            }
        }
        if(!isInvalidRecord) {
            for(int i=record.size();i<columnNames.size();i++){
                record.add(null);
            }
            table.add(record);
        } else {
            throw new RuntimeException("Invalid Record");
        }
    }

    public ArrayList<ArrayList<Object>> getAllRecords() {
        return table;
    }

    public ArrayList<ArrayList<Object>> filterOnColumn(String columnName, Object value) {
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        int index = columnNames.indexOf(columnName);
        for(int i=0;i<table.size();i++){
            if(table.get(i).get(index).equals(value)) {
                result.add(table.get(i));
            }
        }
        return result;
    }

    private boolean isValidEntry(int index, Object object) {
        return columnValidations.get(index).stream()
                .filter(Objects::nonNull)
                .allMatch(validation -> validation.validate(object));
    }

    private Validation getDataTypeValidation(DataType dataType) {
        switch (dataType) {
            case STRING:
                return new StringValidation();
            case INTEGER:
                return new IntValidation();
            default:
                return null;
        }
    }

    private Validation getConstraintValidation(Constraint constraint) {
        switch(constraint) {
            case REQUIRED:
                return new RequiredValidation();
            default:
                return null;
        }
    }




}
