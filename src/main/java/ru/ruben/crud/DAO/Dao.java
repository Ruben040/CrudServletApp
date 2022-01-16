package ru.ruben.crud.DAO;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, ID>{
    T findById(ID id);
    List<T> findAll();
    boolean save(T o);
    boolean update(T o);
    boolean delete(T o);
}
