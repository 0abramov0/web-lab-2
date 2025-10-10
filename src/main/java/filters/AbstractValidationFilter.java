package filters;

import jakarta.servlet.*;
import models.ErrorMessage;
import models.PointValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidationFilter implements Filter {
    public final PointValidator validator;
    public final String attributeName;

    public AbstractValidationFilter(String attributeName) {
        this.attributeName = attributeName;
        this.validator = new PointValidator();
    }

    protected abstract void validateAttribute(String value) throws IllegalArgumentException;

    protected abstract Object castFilteredValues(List<String> values);

    private boolean isValidationError(ServletRequest request) {
        ErrorMessage errorMessage = (ErrorMessage) request.getAttribute("error");
        return errorMessage != null && errorMessage.isError();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String[] values = (String[]) request.getAttribute(attributeName);
        List<String> validatedValues = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage();
        if (isValidationError(request)) {
            chain.doFilter(request, response);
            return;
        }

        if (values != null && values.length > 0) {
            for (String value : values) {
                try {
                    validateAttribute(value);
                    validatedValues.add(value);
                } catch (IllegalArgumentException e) {
                    errorMessage.addMessage(e.getMessage());
                }
            }
            Object filteredValues = castFilteredValues(validatedValues);
            if (filteredValues == null) {
                errorMessage.setError(true);
            }
            request.setAttribute(attributeName, filteredValues);
        } else {
            errorMessage.setError(true);
            errorMessage.addMessage(attributeName.toUpperCase() + " can't be null");
        }
        request.setAttribute("error", errorMessage);
        chain.doFilter(request, response);
    }
}
