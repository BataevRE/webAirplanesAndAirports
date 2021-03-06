package ru.neoflex.courses14.Servlets;

import ru.neoflex.courses14.beans.EntityServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Airports", urlPatterns = "/Airports")
public class Airports extends HttpServlet {
    @EJB
    private EntityServiceLocal entityService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("airports", entityService.getAllAirport());
        request.getRequestDispatcher("Airports.jsp").forward(request, response);
    }
}
