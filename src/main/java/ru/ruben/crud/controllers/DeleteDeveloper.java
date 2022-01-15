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

@WebServlet("/deleteDeveloper")
public class DeleteDeveloper extends HttpServlet {
    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Developer developer = developerDao.findById(id);
            developerDao.delete(developer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/allDevelopers");

    }
}
