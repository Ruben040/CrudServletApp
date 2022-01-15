package ru.ruben.crud.DAO;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, ID>{
    T findById(ID id) throws SQLException;
    List<T> findAll() throws SQLException;
    void save(T o) throws SQLException;
    void update(T o) throws SQLException;
    void delete(T o) throws SQLException;
}
