package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.ProgrammingLanguageDAO;
import ru.ruben.crud.DAO.ProgrammingLanguageDAOImpl;
import ru.ruben.crud.service.DeveloperService;
import ru.ruben.crud.service.DeveloperServiceImpl;
import ru.ruben.crud.service.ProgrammingLanguageService;
import ru.ruben.crud.service.ProgrammingLanguageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteLangDeveloper")
public class DeleteLanguageDeveloperServlet extends HttpServlet {

    private final ProgrammingLanguageService programmingLanguageService = ProgrammingLanguageServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String language = request.getParameter("lang");
        String id = request.getParameter("id");
        programmingLanguageService.deleteLanguageDeveloper(id, language);
        response.sendRedirect(request.getContextPath() + "/updateDeveloper?id=" + id);

    }
}
