package ru.ruben.crud.controllers;

import ru.ruben.crud.service.ProgrammingLanguageService;
import ru.ruben.crud.service.ProgrammingLanguageServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
