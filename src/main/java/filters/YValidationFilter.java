package filters;

public class YValidationFilter extends AbstractValidationFilter {
    public YValidationFilter() {
        super("y");
    }

    @Override
    protected void doValidation(String value) throws IllegalArgumentException {
        validator.validateY(value);
    }
}
