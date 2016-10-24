package ru.neoflex.courses14.Servlets;

import ru.neoflex.courses14.beans.EntityServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddLinkPage", urlPatterns = {"/AirportAddLinkPage", "/AirplaneAddLinkPage"})
public class AddLinkPage extends HttpServlet {
    @EJB
    private EntityServiceLocal entityService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("Airport")) {
            request.setAttribute("airplanes", entityService.getAllAirplane());
            request.getRequestDispatcher("AirportAddLink.jsp").forward(request, response);
        }
        if (request.getRequestURI().contains("Airplane")) {
            request.setAttribute("airports", entityService.getAllAirport());
            request.getRequestDispatcher("AirplaneAddLink.jsp").forward(request, response);
        }
    }
}
