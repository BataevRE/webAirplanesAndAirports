package ru.neoflex.courses14.Servlets;

import ru.neoflex.courses14.beans.EntityServiceLocal;
import ru.neoflex.courses14.jpaEntity.Airport;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindAirport", urlPatterns = "/FindAirport")
public class FindAirport extends HttpServlet {
    @EJB
    private EntityServiceLocal entityService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fieldForSearch = req.getParameter("fieldForSearch");
        String textForSearch = req.getParameter("textForSearch");
        List<Airport> airports = entityService.findAirports(fieldForSearch, textForSearch);
        req.setAttribute("airports", airports);
        req.getRequestDispatcher("Airports.jsp").forward(req, resp);
    }

}
