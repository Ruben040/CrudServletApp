package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.model.ProgrammingLanguage;

import java.util.List;

public interface DeveloperDao extends Dao<Developer, String>{
    void saveWithLanguage(Developer developer, String[] languages);
}
