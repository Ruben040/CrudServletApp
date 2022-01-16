package ru.ruben.crud.controllers;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.service.DeveloperService;
import ru.ruben.crud.service.DeveloperServiceImpl;
import ru.ruben.crud.service.ProgrammingLanguageService;
import ru.ruben.crud.service.ProgrammingLanguageServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addDeveloper")
public class AddDeveloperServlet extends HttpServlet {

    private final DeveloperService developerService = DeveloperServiceImpl.getInstance();
    private final ProgrammingLanguageService programmingLanguageService = ProgrammingLanguageServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> allLanguage = programmingLanguageService.findAllLanguage();
        request.setAttribute("listLang", allLanguage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addDeveloper.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        String[] language = request.getParameterValues("language");
        Developer developer = new Developer(firstName, lastName, age);
        if (language == null) {
            developerService.save(developer);
        }
        else {
            developerService.saveWithLanguage(developer, language);
        }
        response.sendRedirect(request.getContextPath());
    }
}
