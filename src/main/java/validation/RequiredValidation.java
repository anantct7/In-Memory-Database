package validation;

import java.util.Objects;

public class RequiredValidation implements Validation {

    public boolean validate(Object object) {
        return Objects.nonNull(object);
    }
}
