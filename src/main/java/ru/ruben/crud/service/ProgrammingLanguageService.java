package ru.ruben.crud.service;

import ru.ruben.crud.model.Developer;

import java.util.List;
import java.util.Map;

public interface ProgrammingLanguageService {
    List<String> findAllLanguage();
    Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers);
    List<String> findByDeveloper(String id);
    void updateList(Developer developer, String[] id_lang);
    void saveLanguage(String language);
}
