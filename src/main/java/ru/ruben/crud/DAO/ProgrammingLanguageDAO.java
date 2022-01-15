package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ProgrammingLanguageDAO {
    List<String> findAllLanguage() throws SQLException;
    int getIdByLanguage(String language_name) throws SQLException;
    Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers) throws SQLException;
    List<String> findByDeveloper(String id) throws SQLException;
    List<String> findOtherLanguage(String id) throws SQLException;
    void updateList(String id_dev, String[] id_lang) throws SQLException;
    void deleteLanguageDeveloper(String id, String language) throws SQLException;
    void saveLanguage(String language) throws SQLException;

}
