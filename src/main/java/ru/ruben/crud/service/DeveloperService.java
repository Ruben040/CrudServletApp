package ru.ruben.crud.service;

import ru.ruben.crud.model.Developer;

import java.util.List;

public interface DeveloperService {
    Developer findById(String id);
    List<Developer> findAll();
    void save(Developer o, String[] lang);
    void update(Developer o);
    void delete(Developer o);
}
