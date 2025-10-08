package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;
import models.PointValidator;

import java.io.IOException;

public abstract class AbstractValidationFilter extends HttpFilter {
    public final PointValidator validator;
    public final String attributeName;

    public AbstractValidationFilter(String attributeName) {
        this.attributeName = attributeName;
        this.validator = new PointValidator();
    }

    public abstract void doValidation(String value) throws IllegalArgumentException;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String[] values = request.getParameterValues(attributeName);
        if (values != null) {
            try {
                for (String value : values) {
                    doValidation(value);
                }
            } catch (IllegalArgumentException e) {
                request.setAttribute("validationError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        chain.doFilter(request, response);
    }
}
