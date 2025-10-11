package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LastModificationFilter implements Filter {
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(!"GET".equals(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        Long lastModified = (Long) servletContext.getAttribute("lastModified");
        if (lastModified != null) {
            try {
                long ifModifiedSince = httpRequest.getDateHeader("If-Modified-Since");
                if (ifModifiedSince != -1 && ifModifiedSince >= lastModified) {
                    httpResponse.sendError(HttpServletResponse.SC_NOT_MODIFIED);
                }
            } catch (Exception e) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
            }
        }
        chain.doFilter(request, response);
    }
}
