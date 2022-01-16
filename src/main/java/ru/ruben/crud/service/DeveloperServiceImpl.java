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
        return developerDao.findById(id);
    }

    @Override
    public List<Developer> findAll(){
        return developerDao.findAll();
    }

    @Override
    public boolean save(Developer o, String[] language){
        if (language == null) {
            return developerDao.save(o);
        }
        else return developerDao.saveWithLanguage(o, language);
    }

    @Override
    public boolean update(Developer o){
       return developerDao.update(o);
    }

    @Override
    public boolean delete(Developer o) {
        return developerDao.delete(o);
    }
}
