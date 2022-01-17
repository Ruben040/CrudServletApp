package ru.ruben.crud.service;


import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.DAO.ProgrammingLanguageDAO;
import ru.ruben.crud.DAO.ProgrammingLanguageDAOImpl;
import ru.ruben.crud.model.Developer;
import ru.ruben.crud.model.ProgrammingLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

    private final ProgrammingLanguageDAO programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();
    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();


    private static class SingletonProgrammingLanguageService{
        private static final ProgrammingLanguageServiceImpl INIT = new ProgrammingLanguageServiceImpl();
    }

    public static ProgrammingLanguageServiceImpl getInstance(){
        return ProgrammingLanguageServiceImpl.SingletonProgrammingLanguageService.INIT;
    }

    @Override
    public List<String> findAllLanguage() {
        return programmingLanguageDAO.findAllLanguage().stream().map(ProgrammingLanguage::getLanguageName).collect(Collectors.toList());
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
    public void updateList(Developer developer, String[] id_lang) {
        if (id_lang != null) {
            programmingLanguageDAO.updateList(developer, id_lang);
        }
        else developerDao.update(developer);
    }

    @Override
    public void deleteLanguageDeveloper(String id, String language) {
        programmingLanguageDAO.deleteLanguageDeveloper(id, language);
    }


    @Override
    public void saveLanguage(String language) {
        List<String> languages = findAllLanguage();
        List<String> allLanguage = new ArrayList<>(languages);
        if (!allLanguage.contains(language)) {
            ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(language);
            programmingLanguageDAO.saveLanguage(programmingLanguage);
        }
    }
}
