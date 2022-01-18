package ru.ruben.crud.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ruben.crud.model.DevProgrammingLang;
import ru.ruben.crud.model.DevProgrammingLangId;
import ru.ruben.crud.model.Developer;
import ru.ruben.crud.util.HibernateConnector;


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
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Developer developer = session.get(Developer.class, Integer.parseInt(id));
            session.getTransaction().commit();
            session.close();
            return developer;
        } catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Developer> findAll(){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Developer> from_developers = session.createQuery("from Developers", Developer.class).list();
            session.getTransaction().commit();
            session.close();
            return from_developers;
        }
        catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public int save(Developer developer){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            int id = (Integer) session.save(developer);
            session.getTransaction().commit();
            session.close();
            return id;
        }
        catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void update(Developer developer){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            session.beginTransaction().rollback();
            session.close();
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Developer developer){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(developer);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            session.beginTransaction().rollback();
            session.close();
            e.printStackTrace();
        }

    }


    @Override
    public void saveWithLanguage(Developer developer, String[] languages) {
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        int save = 0;
        try {
            session.beginTransaction();
            save = (Integer) session.save(developer);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }

        DevProgrammingLang devProgrammingLang = new DevProgrammingLang();
        DevProgrammingLangId devProgrammingLangId = new DevProgrammingLangId();
        devProgrammingLangId.setDeveloperId(save);

        for (String lang: languages){
            int idByLanguage = programmingLanguageDAO.getIdByLanguage(lang);
            devProgrammingLangId.setProgLangId(idByLanguage);
            devProgrammingLang.setId(devProgrammingLangId);
            Session session1 = sessionFactory.openSession();
            try {
                session1.beginTransaction();
                session1.save(devProgrammingLang);
                session1.getTransaction().commit();
                session1.close();
            }
            catch (Exception e){
                session1.getTransaction().rollback();
                session1.close();
                e.printStackTrace();
            }

        }
    }
}
