package filters;

public class YValidationFilter extends AbstractValidationFilter {
    public YValidationFilter() {
        super("y");
    }

    @Override
    public void doValidation(String value) throws IllegalArgumentException{
        validator.validateY(value);
    }
}
