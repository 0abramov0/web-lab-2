package filters;

public class RValidationFilter extends AbstractValidationFilter {
    public RValidationFilter() {
        super("r");
    }

    @Override
    public void doValidation(String value) throws IllegalArgumentException {
        validator.validateR(value);
    }
}
