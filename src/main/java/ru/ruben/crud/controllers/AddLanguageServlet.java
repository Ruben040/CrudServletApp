package ru.ruben.crud.controllers;

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

@WebServlet("/addLanguage")
public class AddLanguageServlet extends HttpServlet {
    private final ProgrammingLanguageService programmingLanguageService = ProgrammingLanguageServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addLanguage.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String language = request.getParameter("language");
        List<String> allLanguage = programmingLanguageService.findAllLanguage();
        if (!allLanguage.contains(language)){
            programmingLanguageService.saveLanguage(language);
        }
        response.sendRedirect(request.getContextPath());
    }
}
