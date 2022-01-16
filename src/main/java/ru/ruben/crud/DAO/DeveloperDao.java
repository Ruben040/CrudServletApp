package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;

import java.sql.SQLException;

public interface DeveloperDao extends Dao<Developer, String>{
    boolean saveWithLanguage(Developer developer, String[] languages);
}
