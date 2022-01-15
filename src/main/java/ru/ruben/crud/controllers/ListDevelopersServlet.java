package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.model.Developer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/allDevelopers")
public class ListDevelopersServlet extends HttpServlet {
    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Developer> all = developerDao.findAll();
            request.setAttribute("developers", all);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("listDevelopers.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
