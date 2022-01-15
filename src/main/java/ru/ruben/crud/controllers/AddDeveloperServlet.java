package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.model.Developer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addDeveloper")
public class AddDeveloperServlet extends HttpServlet {
    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addDeveloper.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        try {
            developerDao.save(new Developer(firstName, lastName, age));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath());
    }
}
