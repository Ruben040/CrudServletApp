package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
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

@WebServlet("/addDeveloper")
public class AddDeveloperServlet extends HttpServlet {
    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();
    private final ProgrammingLanguageDAOImpl programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<String> allLanguage = programmingLanguageDAO.findAllLanguage();
            request.setAttribute("listLang", allLanguage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            try {
                developerDao.save(developer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                developerDao.saveWithLanguage(developer, language);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect(request.getContextPath());
    }
}
