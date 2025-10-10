package filters;

import java.util.List;

public class YValidationFilter extends AbstractValidationFilter {
    public YValidationFilter() {
        super("y");
    }

    @Override
    protected void validateAttribute(String value) throws IllegalArgumentException {
        validator.validateY(value);
    }

    @Override
    protected Object filterValues(List<String> values) {
        if (values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }
}
