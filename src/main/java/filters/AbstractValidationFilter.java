package filters;

import jakarta.servlet.*;
import models.PointValidator;

import java.io.IOException;

public abstract class AbstractValidationFilter implements Filter {
    public final PointValidator validator;
    public final String attributeName;

    public AbstractValidationFilter(String attributeName) {
        this.attributeName = attributeName;
        this.validator = new PointValidator();
    }

    protected abstract void doValidation(String value) throws IllegalArgumentException;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String[] values = request.getParameterValues(attributeName);

        Boolean isValidationError = (Boolean) request.getAttribute("validationError");
        if (isValidationError != null && isValidationError) {
            chain.doFilter(request, response);
            return;
        }
        if (values != null && values.length > 0) {
            try {
                for (String value : values) {
                    doValidation(value);
                }
            } catch (IllegalArgumentException e) {
                request.setAttribute("validationError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        } else {
            request.setAttribute("validationError", true);
            request.setAttribute("errorMessage", attributeName + " can't be null");
        }
        chain.doFilter(request, response);
    }
}
