package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.ProgrammingLanguageDAOImpl;
import ru.ruben.crud.model.Developer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addLanguage")
public class AddLanguageServlet extends HttpServlet {
    private final ProgrammingLanguageDAOImpl programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addLanguage.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String language = request.getParameter("language");
        try {
            List<String> allLanguage = programmingLanguageDAO.findAllLanguage();
            if (!allLanguage.contains(language)){
                programmingLanguageDAO.saveLanguage(language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath());
    }
}
