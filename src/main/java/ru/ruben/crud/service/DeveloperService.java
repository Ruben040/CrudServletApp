package ru.ruben.crud.service;

import ru.ruben.crud.model.Developer;

import java.util.List;

public interface DeveloperService {
    Developer findById(String id);
    List<Developer> findAll();
    boolean save(Developer o, String[] lang);
    boolean update(Developer o);
    boolean delete(Developer o);
}
