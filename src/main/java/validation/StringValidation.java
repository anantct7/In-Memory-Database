package validation;

import entities.ColumnInfo;

public class StringValidation implements Validation {

    public boolean validate(Object object) {
        return (object==null) || (object instanceof String) && (((String)object).length() <=20);
    }
}
