package ru.ruben.crud.service;

import ru.ruben.crud.model.Developer;

import java.sql.SQLException;
import java.util.List;

public interface DeveloperService {
    Developer findById(String id);
    List<Developer> findAll();
    void save(Developer o);
    void update(Developer o);
    void delete(Developer o);
    void saveWithLanguage(Developer developer, String[] languages);
}
