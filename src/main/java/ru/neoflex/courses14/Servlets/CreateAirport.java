package ru.neoflex.courses14.Servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.beans.AirportManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateAirport", urlPatterns = {"/CreateAirport", "/UpdateAirport", "/CancelSaveAirport","/AddLinkToAirport","/DeleteLinkFromAirport"})
public class CreateAirport extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CreateAirport.class);

    @EJB
    private AirportManager airportManager;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String airportId = request.getParameter("airportId");
        String city = request.getParameter("city");
        String codeIATA = request.getParameter("codeIATA");
        String throughput = request.getParameter("throughput");
        if (city.equals("") || codeIATA.equals("") || throughput.equals("")) {
            request.setAttribute("error", "значения полей не должны быть пустыми");
            request.getRequestDispatcher("CreateOrUpdateAirport.jsp").forward(request, response);
            return;
        }
        log.debug(airportId + city + codeIATA + throughput);
        airportManager.setAirport(airportId, city, codeIATA, throughput);
        airportManager.saveAirport();
        response.sendRedirect("Airports");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("airportId")) {
            log.debug("airportId: "+request.getParameter("airportId"));
            airportManager.setAirport(request.getParameter("airportId"));
            request.setAttribute("airport", airportManager.getAirport());
            request.setAttribute("airplanes", airportManager.getAirplanes());
        }
        request.getSession().setAttribute("action", request.getParameter("action"));
        request.getRequestDispatcher("CreateOrUpdateAirport.jsp").forward(request, response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("CancelSaveAirport")) {
            airportManager.end();
            response.sendRedirect("Airports");
        } else if (request.getRequestURI().endsWith("AddLinkToAirport")) {
            airportManager.addAirplane(request.getParameter("airplaneToAdd"));
            request.setAttribute("airport", airportManager.getAirport());
            request.setAttribute("airplanes", airportManager.getAirplanes());
            request.getRequestDispatcher("CreateOrUpdateAirport.jsp").forward(request, response);
        } else if (request.getRequestURI().endsWith("DeleteLinkFromAirport")) {
            airportManager.deleteAirplane(request.getParameter("airplaneToDelete"));
            request.setAttribute("airport", airportManager.getAirport());
            request.setAttribute("airplanes", airportManager.getAirplanes());
            request.getRequestDispatcher("CreateOrUpdateAirport.jsp").forward(request, response);
        } else {
            super.service(request, response);
        }
    }
}
