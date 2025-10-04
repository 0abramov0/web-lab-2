package controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.PointValidator;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private final PointValidator validator = new PointValidator();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String[] xValues = request.getParameterValues("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");

        ServletContext context = getServletContext();
        context.setAttribute("isError", false);

        Boolean isValidationError = (Boolean) request.getAttribute("validationError");
        if(isValidationError != null && isValidationError) {
            System.out.println("Error happened: " + request.getAttribute("errorMessage"));
            context.setAttribute("isError", true);
            context.setAttribute("errorMessage", request.getAttribute("errorMessage"));
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        } else{
            context.setAttribute("x", xValues);
            context.setAttribute("y", y);
            context.setAttribute("r", r);
            context.setAttribute("allowedFromController", true);
            request.getRequestDispatcher("./area-check").forward(request, response);
        }
    }

}
