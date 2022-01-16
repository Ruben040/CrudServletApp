package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.model.Developer;
import ru.ruben.crud.service.DeveloperService;
import ru.ruben.crud.service.DeveloperServiceImpl;
import ru.ruben.crud.service.ProgrammingLanguageService;
import ru.ruben.crud.service.ProgrammingLanguageServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteDeveloper")
public class DeleteDeveloper extends HttpServlet {
    private final DeveloperService developerService = DeveloperServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        Developer developer = developerService.findById(id);
        developerService.delete(developer);
        response.sendRedirect(request.getContextPath() + "/allDevelopers");

    }
}
