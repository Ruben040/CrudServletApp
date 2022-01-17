package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.model.ProgrammingLanguage;

import java.util.List;
import java.util.Map;

public interface ProgrammingLanguageDAO {
    List<ProgrammingLanguage> findAllLanguage();
    int getIdByLanguage(String language_name);
    Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers);
    List<String> findByDeveloper(String id);
    void updateList(Developer developer, String[] id_lang);
    void deleteLanguageDeveloper(String id, String language);
    void saveLanguage(ProgrammingLanguage language);

}
