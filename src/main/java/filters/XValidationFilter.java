package filters;

public class XValidationFilter extends AbstractValidationFilter {
    public XValidationFilter() {
        super("x");
    }

    @Override
    public void doValidation(String value) throws IllegalArgumentException{
        validator.validateX(value);
    }
}
