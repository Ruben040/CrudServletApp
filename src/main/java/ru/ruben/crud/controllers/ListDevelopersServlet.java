package ru.ruben.crud.controllers;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.service.DeveloperService;
import ru.ruben.crud.service.DeveloperServiceImpl;
import ru.ruben.crud.service.ProgrammingLanguageService;
import ru.ruben.crud.service.ProgrammingLanguageServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/allDevelopers")
public class ListDevelopersServlet extends HttpServlet {
    private final DeveloperService developerService = DeveloperServiceImpl.getInstance();
    private final ProgrammingLanguageService programmingLanguageService = ProgrammingLanguageServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Developer> all = developerService.findAll();
        Map<Integer, List<String>> languageByDeveloper = programmingLanguageService.getLanguageByDevelopers(all);
        request.setAttribute("developers", all);
        request.setAttribute("language", languageByDeveloper);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listDevelopers.jsp");
        requestDispatcher.forward(request, response);
    }
}
