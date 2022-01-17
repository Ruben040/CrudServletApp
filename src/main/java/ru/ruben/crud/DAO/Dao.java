package ru.ruben.crud.DAO;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, ID>{
    T findById(ID id);
    List<T> findAll();
    int save(T o);
    void update(T o);
    void delete(T o);
}
