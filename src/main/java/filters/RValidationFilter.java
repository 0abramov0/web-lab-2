package filters;

import java.util.List;

public class RValidationFilter extends AbstractValidationFilter {
    public RValidationFilter() {
        super("r");
    }

    @Override
    protected void validateAttribute(String value) throws IllegalArgumentException {
        validator.validateR(value);
    }

    @Override
    protected Object castFilteredValues(List<String> values) {
        if (values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }
}
