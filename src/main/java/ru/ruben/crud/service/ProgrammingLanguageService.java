package ru.ruben.crud.service;

import ru.ruben.crud.model.Developer;

import java.util.List;
import java.util.Map;

public interface ProgrammingLanguageService {
    List<String> findAllLanguage();
    int getIdByLanguage(String language_name);
    Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers);
    List<String> findByDeveloper(String id);
    List<String> findOtherLanguage(String id);
    void updateList(String id_dev, String[] id_lang);
    void deleteLanguageDeveloper(String id, String language);
    void saveLanguage(String language);
}
