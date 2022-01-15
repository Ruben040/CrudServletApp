package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.model.Developer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateDeveloper")
public class UpdateDeveloperServlet extends HttpServlet {

    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();
    String id;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        try {
            developerDao.update(new Developer(Integer.parseInt(id),firstName, lastName, age));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/allDevelopers");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        id = request.getParameter("id");
        try {
            Developer developer = developerDao.findById(id);
            request.setAttribute("dev", developer);
            request.getRequestDispatcher("updateDeveloper.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
