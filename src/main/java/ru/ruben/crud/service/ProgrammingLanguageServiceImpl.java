package ru.ruben.crud.service;


import ru.ruben.crud.DAO.ProgrammingLanguageDAO;
import ru.ruben.crud.DAO.ProgrammingLanguageDAOImpl;
import ru.ruben.crud.model.Developer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

    private final ProgrammingLanguageDAO programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();

    private static class SingletonProgrammingLanguageService{
        private static final ProgrammingLanguageServiceImpl INIT = new ProgrammingLanguageServiceImpl();
    }

    public static ProgrammingLanguageServiceImpl getInstance(){
        return ProgrammingLanguageServiceImpl.SingletonProgrammingLanguageService.INIT;
    }

    @Override
    public List<String> findAllLanguage() {
        try {
            return programmingLanguageDAO.findAllLanguage();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int getIdByLanguage(String language_name) {
        try {
            return programmingLanguageDAO.getIdByLanguage(language_name);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers) {
        try {
            return programmingLanguageDAO.getLanguageByDevelopers(developers);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<String> findByDeveloper(String id) {
        try {
            return programmingLanguageDAO.findByDeveloper(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<String> findOtherLanguage(String id) {
        try {
            return programmingLanguageDAO.findOtherLanguage(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void updateList(String id_dev, String[] id_lang) {
        try {
            programmingLanguageDAO.updateList(id_dev, id_lang);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLanguageDeveloper(String id, String language) {
        try {
            programmingLanguageDAO.deleteLanguageDeveloper(id, language);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveLanguage(String language) {
        try {
            programmingLanguageDAO.saveLanguage(language);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
