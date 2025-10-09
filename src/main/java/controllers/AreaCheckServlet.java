package controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

import java.io.IOException;

@WebServlet("/area-check")
public class AreaCheckServlet extends HttpServlet {
    private final PointValidator validator = new PointValidator();
    private final HitChecker hitChecker = new HitChecker();
    private final AreaCheckResultRepository repository = new AreaCheckResultRepository();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!isValidAccess()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "Direct access to this servlet is not allowed");
            return;
        }
        processRequest(request, response);
    }

    private boolean isValidAccess() {
        ServletContext context = getServletContext();
        Boolean isAllowedFromController = (Boolean) context.getAttribute("allowedFromController");
        context.removeAttribute("allowedFromController");
        return isAllowedFromController != null && isAllowedFromController;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext context = getServletContext();

        String[] xValues = (String[]) context.getAttribute("x");
        String y = (String) context.getAttribute("y");
        String r = (String) context.getAttribute("r");

        for (String x : xValues) {
            Point point = new Point(x, y, r);
            boolean isHit = hitChecker.checkHit(point);
            AreaCheckResult result = new AreaCheckResult(point, isHit);
            repository.addResult(result);
        }

        context.setAttribute("repository", repository);
        request.getRequestDispatcher("./index.jsp").forward(request, response);
    }
}
