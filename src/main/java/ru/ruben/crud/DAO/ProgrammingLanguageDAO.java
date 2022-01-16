package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ProgrammingLanguageDAO {
    List<String> findAllLanguage();
    int getIdByLanguage(String language_name);
    Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers);
    List<String> findByDeveloper(String id);
    List<String> findOtherLanguage(String id);
    boolean updateList(String id_dev, String[] id_lang);
    boolean deleteLanguageDeveloper(String id, String language);
    boolean saveLanguage(String language);

}
