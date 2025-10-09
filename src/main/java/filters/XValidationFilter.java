package filters;

import java.util.List;

public class XValidationFilter extends AbstractValidationFilter {
    public XValidationFilter() {
        super("x");
    }

    @Override
    protected void doValidation(String value) throws IllegalArgumentException {
        validator.validateX(value);
    }

    @Override
    protected Object filterValues(List<String> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.toArray(new String[0]);
    }
}
