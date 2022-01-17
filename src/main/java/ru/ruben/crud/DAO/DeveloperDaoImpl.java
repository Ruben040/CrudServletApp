package ru.ruben.crud.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ruben.crud.model.DevProgrammingLang;
import ru.ruben.crud.model.DevProgrammingLangId;
import ru.ruben.crud.model.Developer;
import ru.ruben.crud.model.ProgrammingLanguage;
import ru.ruben.crud.util.HibernateConnector;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    private final ProgrammingLanguageDAO programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();

    private static class SingletonDao {
        private static final DeveloperDaoImpl INIT = new DeveloperDaoImpl();
    }

    public static DeveloperDaoImpl getInstance() {
        return SingletonDao.INIT;
    }

    @Override
    public Developer findById(String id){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        Developer developer = currentSession.get(Developer.class, Integer.parseInt(id));
        transaction.commit();
        currentSession.close();
        return developer;
    }

    @Override
    public List<Developer> findAll(){

        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        List<Developer> from_developers = currentSession.createQuery("from Developers", Developer.class).list();
        transaction.commit();
        currentSession.close();
        return from_developers;
    }

    @Override
    public int save(Developer developer){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        int id = (Integer) currentSession.save(developer);
        transaction.commit();
        currentSession.close();
        return id;
    }

    @Override
    public void update(Developer developer){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(developer);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void delete(Developer developer){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(developer);
        transaction.commit();
        currentSession.close();
    }


    @Override
    public void saveWithLanguage(Developer developer, String[] languages) {
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int save = (Integer) session.save(developer);
        transaction.commit();
        session.close();
        DevProgrammingLang devProgrammingLang = new DevProgrammingLang();
        DevProgrammingLangId devProgrammingLangId = new DevProgrammingLangId();
        devProgrammingLangId.setDeveloperId(save);
        for (String lang: languages){
            int idByLanguage = programmingLanguageDAO.getIdByLanguage(lang);
            devProgrammingLangId.setProgLangId(idByLanguage);
            devProgrammingLang.setId(devProgrammingLangId);
            Session session1 = sessionFactory.openSession();
            Transaction transaction1 = session1.beginTransaction();
            session1.save(devProgrammingLang);
            transaction1.commit();
            session1.close();
        }
    }
}
