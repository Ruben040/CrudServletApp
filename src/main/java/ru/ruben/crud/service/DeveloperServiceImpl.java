package ru.ruben.crud.service;

import ru.ruben.crud.DAO.DeveloperDao;
import ru.ruben.crud.DAO.DeveloperDaoImpl;
import ru.ruben.crud.model.Developer;

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
    public void save(Developer o, String[] language){
        if (language != null) {
            developerDao.saveWithLanguage(o, language);
        }
        else developerDao.save(o);
    }

    @Override
    public void update(Developer o){
       developerDao.update(o);
    }

    @Override
    public void delete(Developer o) {
        developerDao.delete(o);
    }
}
