package ru.neoflex.courses14.Servlets;

import ru.neoflex.courses14.beans.EntityServiceLocal;
import ru.neoflex.courses14.jpaEntity.Airplane;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindAirplane", urlPatterns = "/FindAirplane")
public class FindAirplane extends HttpServlet {
    @EJB
    private EntityServiceLocal entityService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fieldForSearch = req.getParameter("fieldForSearch");
        String textForSearch = req.getParameter("textForSearch");
        List<Airplane> airplanes = entityService.findAirplanes(fieldForSearch, textForSearch);
        req.setAttribute("airplanes", airplanes);
        req.getRequestDispatcher("Airplanes.jsp").forward(req, resp);
    }

}
