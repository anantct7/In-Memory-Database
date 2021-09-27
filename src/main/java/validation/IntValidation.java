package validation;

public class IntValidation implements Validation {

    public boolean validate(Object object) {
        return (object==null) || (object instanceof Integer) && checkInRange((Integer) object);
    }

    private boolean checkInRange(Integer value) {
        return ((value>=-1024) && (value<=1024));
    }
}
