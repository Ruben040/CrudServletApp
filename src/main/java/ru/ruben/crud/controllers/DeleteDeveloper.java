package ru.ruben.crud.controllers;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.service.DeveloperService;
import ru.ruben.crud.service.DeveloperServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
