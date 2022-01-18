package ru.ruben.crud.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.ruben.crud.model.DevProgrammingLang;
import ru.ruben.crud.model.DevProgrammingLangId;
import ru.ruben.crud.model.Developer;
import ru.ruben.crud.model.ProgrammingLanguage;
import ru.ruben.crud.util.HibernateConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgrammingLanguageDAOImpl implements ProgrammingLanguageDAO{

    private static class SingletonLangDao{
        private static final ProgrammingLanguageDAOImpl INIT = new ProgrammingLanguageDAOImpl();
    }

    public static ProgrammingLanguageDAOImpl getInstance(){
        return ProgrammingLanguageDAOImpl.SingletonLangDao.INIT;
    }

    @Override
    public List<ProgrammingLanguage> findAllLanguage(){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<ProgrammingLanguage> language = session.createQuery("from ProgrammingLanguage", ProgrammingLanguage.class).list();
            session.getTransaction().commit();
            session.close();
            return language;
        }
        catch (Exception e){
            session.beginTransaction().rollback();
            session.close();
            return null;
        }
    }

    @Override
    public int getIdByLanguage(String language_name){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query<ProgrammingLanguage> query = session.createQuery("from ProgrammingLanguage where languageName =:lang", ProgrammingLanguage.class);
            query.setParameter("lang", language_name);
            ProgrammingLanguage language = query.setMaxResults(1).getSingleResult();
            int identifier = language.getId();
            session.getTransaction().commit();
            session.close();
            return identifier;
        }
        catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers){
        Map<Integer, List<String>> map = new HashMap<>();
        for(Developer developer: developers){
            int id = developer.getId();
            List<String> byDeveloper = findByDeveloper(String.valueOf(id));
            map.put(id, byDeveloper);
        }
        return map;
    }

    @Override
    public List<String> findByDeveloper(String id){
        List<String> lang = new ArrayList<>();
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            session.beginTransaction();
            Developer developer = session.get(Developer.class, Integer.parseInt(id));
            List<ProgrammingLanguage> programmingLanguages = developer.getProgrammingLanguages();
            for (ProgrammingLanguage programmingLanguage : programmingLanguages) {
                lang.add(programmingLanguage.getLanguageName());
            }
            session.getTransaction().commit();
            session.close();
            return lang;
        }
        catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void updateList(Developer developer, String[] languages){
        int id = developer.getId();
        DevProgrammingLang devProgrammingLang = new DevProgrammingLang();
        DevProgrammingLangId devProgrammingLangId = new DevProgrammingLangId();
        devProgrammingLangId.setDeveloperId(id);
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
            for (String lang : languages) {
                session.beginTransaction();
                devProgrammingLangId.setProgLangId(getIdByLanguage(lang));
                devProgrammingLang.setId(devProgrammingLangId);
                session.merge(devProgrammingLang);
                session.getTransaction().commit();
            }
            session.close();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
    }

    @Override
    public void saveLanguage(ProgrammingLanguage language) {
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(language);
            session.beginTransaction().commit();
            session.close();
        }
        catch (Exception e){
            session.getTransaction().commit();
            session.close();
            e.printStackTrace();
        }
    }
}
