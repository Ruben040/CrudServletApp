package ru.ruben.crud.service;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.model.Developer;

import java.sql.SQLException;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperDao developerDao = DeveloperDaoImpl.getInstance();

    private static class SingletonDeveloperService{
        private static final DeveloperServiceImpl INIT = new DeveloperServiceImpl();
    }

    public static DeveloperServiceImpl getInstance(){
        return DeveloperServiceImpl.SingletonDeveloperService.INIT;
    }

    @Override
    public Developer findById(String id){
        try {
            return developerDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Developer> findAll(){
        try {
            return developerDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void save(Developer o){
        try {
            developerDao.save(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer o){
        try {
            developerDao.update(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developer o) {
        try {
            developerDao.delete(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveWithLanguage(Developer developer, String[] languages) {
        try {
            developerDao.saveWithLanguage(developer, languages);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
