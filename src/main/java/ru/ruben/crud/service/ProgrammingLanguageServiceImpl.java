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
        return programmingLanguageDAO.findAllLanguage();
    }

    @Override
    public int getIdByLanguage(String language_name) {
        return programmingLanguageDAO.getIdByLanguage(language_name);
    }

    @Override
    public Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers) {
        return programmingLanguageDAO.getLanguageByDevelopers(developers);
    }

    @Override
    public List<String> findByDeveloper(String id) {
        return programmingLanguageDAO.findByDeveloper(id);
    }

    @Override
    public List<String> findOtherLanguage(String id) {
        return programmingLanguageDAO.findOtherLanguage(id);
    }

    @Override
    public boolean updateList(String id_dev, String[] id_lang) {
        return programmingLanguageDAO.updateList(id_dev, id_lang);
    }

    @Override
    public boolean deleteLanguageDeveloper(String id, String language) {
        return programmingLanguageDAO.deleteLanguageDeveloper(id, language);
    }

    @Override
    public boolean saveLanguage(String language) {
        List<String> allLanguage = programmingLanguageDAO.findAllLanguage();
        if (!allLanguage.contains(language)) {
            return programmingLanguageDAO.saveLanguage(language);
        }
        return false;
    }
}
