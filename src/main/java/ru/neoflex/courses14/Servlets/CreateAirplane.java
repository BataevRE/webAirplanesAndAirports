package ru.neoflex.courses14.Servlets;

import ru.neoflex.courses14.beans.AirplaneManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CreateAirplane", urlPatterns = {"/CreateAirplane", "/UpdateAirplane", "/CancelSaveAirplane", "/AddLinkToAirplane", "/DeleteLinkFromAirplane"})
public class CreateAirplane extends HttpServlet {
    @EJB
    private AirplaneManager airplaneManager;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String airplaneId = request.getParameter("airplaneId");
        String serialNumber = request.getParameter("serialNumber");
        String model = request.getParameter("model");
        String destination = request.getParameter("destination");
        String releaseDate = request.getParameter("releaseDate");
        String operator = request.getParameter("operator");
        if (serialNumber.equals("") || model.equals("") || destination.equals("") ||
                releaseDate.equals("") || operator.equals("")) {
            request.setAttribute("error", "значения полей не должны быть пустыми");
            request.getRequestDispatcher("CreateOrUpdateAirplane.jsp").forward(request, response);
            return;
        }
        airplaneManager.setAirplane(airplaneId, serialNumber, model, destination, releaseDate, operator);
        airplaneManager.saveAirplane();
        response.sendRedirect("Airplanes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("airplaneId")) {
            airplaneManager.setAirplane(request.getParameter("airplaneId"));
            request.setAttribute("airplane", airplaneManager.getAirplane());
            request.setAttribute("airports", airplaneManager.getAirports());
        }
        request.getSession().setAttribute("action", request.getParameter("action"));
        request.getRequestDispatcher("CreateOrUpdateAirplane.jsp").forward(request, response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("CancelSaveAirplane")) {
            airplaneManager.end();
            response.sendRedirect("Airplanes");
        } else if (request.getRequestURI().endsWith("AddLinkToAirplane")) {
            airplaneManager.addAirport(request.getParameter("airportToAdd"));
            request.setAttribute("airplane", airplaneManager.getAirplane());
            request.setAttribute("airports", airplaneManager.getAirports());
            request.getRequestDispatcher("CreateOrUpdateAirplane.jsp").forward(request, response);
        } else if (request.getRequestURI().endsWith("DeleteLinkFromAirplane")) {
            airplaneManager.deleteAirport(request.getParameter("airportToDelete"));
            request.setAttribute("airplane", airplaneManager.getAirplane());
            request.setAttribute("airports", airplaneManager.getAirports());
            request.getRequestDispatcher("CreateOrUpdateAirplane.jsp").forward(request, response);
        } else {
            super.service(request, response);
        }
    }
}
