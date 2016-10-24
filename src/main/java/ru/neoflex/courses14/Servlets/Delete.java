package ru.neoflex.courses14.Servlets;

import ru.neoflex.courses14.beans.EntityServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete", urlPatterns = "/Delete")
public class Delete extends HttpServlet {
    @EJB
    private EntityServiceLocal entityService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entity = request.getParameter("entity");
        switch (entity) {
            case "airport":
                entityService.removeAirport(request.getParameter("airportId"));
                response.sendRedirect("Airports");
                break;
            case "airplane":
                entityService.removeAirplane(request.getParameter("airplaneId"));
                response.sendRedirect("Airplanes");
                break;
        }
    }
}
