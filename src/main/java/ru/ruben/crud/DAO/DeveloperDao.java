package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;

import java.sql.SQLException;

public interface DeveloperDao extends Dao<Developer, String>{
    void saveWithLanguage(Developer developer, String[] languages) throws SQLException;
}
