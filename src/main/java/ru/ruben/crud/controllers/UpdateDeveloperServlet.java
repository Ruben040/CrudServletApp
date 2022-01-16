package ru.ruben.crud.controllers;

import ru.ruben.crud.model.Developer;
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
import java.util.List;

@WebServlet("/updateDeveloper")
public class UpdateDeveloperServlet extends HttpServlet {
    private final DeveloperService developerService = DeveloperServiceImpl.getInstance();
    private final ProgrammingLanguageService programmingLanguageService = ProgrammingLanguageServiceImpl.getInstance();

    String id;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String[] languages = request.getParameterValues("language");
        if (languages != null){
            programmingLanguageService.updateList(id, languages);
        }
        int age = Integer.parseInt(request.getParameter("age"));
        developerService.update(new Developer(Integer.parseInt(id),firstName, lastName, age));

        response.sendRedirect(request.getContextPath() + "/allDevelopers");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        Developer developer = developerService.findById(id);
        List<String> lang= programmingLanguageService.findByDeveloper(id);
        List<String> otherLanguage = programmingLanguageService.findOtherLanguage(id);
        request.setAttribute("dev", developer);
        request.setAttribute("lang", lang);
        request.setAttribute("otherLang", otherLanguage);
        request.getRequestDispatcher("updateDeveloper.jsp")
                .forward(request, response);
    }


}
