package ru.ruben.crud.controllers;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.DAO.ProgrammingLanguageDAO;
import ru.ruben.crud.DAO.ProgrammingLanguageDAOImpl;
import ru.ruben.crud.model.Developer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/updateDeveloper")
public class UpdateDeveloperServlet extends HttpServlet {
    private final ProgrammingLanguageDAO programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();
    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();

    String id;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String[] languages = request.getParameterValues("language");
        if (languages != null){
            try {
                programmingLanguageDAO.updateList(id, languages);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int age = Integer.parseInt(request.getParameter("age"));
        try {
            developerDao.update(new Developer(Integer.parseInt(id),firstName, lastName, age));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/allDevelopers");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        try {
            Developer developer = developerDao.findById(id);
            List<String> lang= programmingLanguageDAO.findByDeveloper(id);
            List<String> otherLanguage = programmingLanguageDAO.findOtherLanguage(id);
            request.setAttribute("dev", developer);
            request.setAttribute("lang", lang);
            request.setAttribute("otherLang", otherLanguage);
            request.getRequestDispatcher("updateDeveloper.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
