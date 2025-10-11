package controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ErrorMessage;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./repository.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext context = getServletContext();

        ErrorMessage errorMessage = (ErrorMessage) request.getAttribute("error");
        context.setAttribute("error", errorMessage);
        if (errorMessage != null && errorMessage.isError()) {
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        } else {
            context.setAttribute("allowedFromController", true);
            request.getRequestDispatcher("./area-check").forward(request, response);
        }
    }
}
