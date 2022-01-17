package ru.ruben.crud.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        Transaction transaction = session.beginTransaction();
        List<ProgrammingLanguage> language = session.createQuery("from ProgrammingLanguage", ProgrammingLanguage.class).list();
        transaction.commit();
        session.close();
        return language;
    }

    @Override
    public int getIdByLanguage(String language_name){
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<ProgrammingLanguage> query = session.createQuery("from ProgrammingLanguage where languageName =:lang", ProgrammingLanguage.class);
        query.setParameter("lang", language_name);
        ProgrammingLanguage language =(ProgrammingLanguage) query.setMaxResults(1).getSingleResult();
        int identifier = language.getId();
        transaction.commit();
        session.close();
        return identifier;
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
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        Developer developer = currentSession.get(Developer.class, Integer.parseInt(id));
        List<ProgrammingLanguage> programmingLanguages = developer.getProgrammingLanguages();
        for (ProgrammingLanguage programmingLanguage: programmingLanguages){
            lang.add(programmingLanguage.getLanguageName());
        }
        transaction.commit();
        currentSession.close();
        return lang;
    }


    @Override
    public void updateList(Developer developer, String[] languages){
        int id = developer.getId();
        DevProgrammingLang devProgrammingLang = new DevProgrammingLang();
        DevProgrammingLangId devProgrammingLangId = new DevProgrammingLangId();
        devProgrammingLangId.setDeveloperId(id);
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(developer);
        session.getTransaction().commit();
        for (String lang: languages){
            session.beginTransaction();
            devProgrammingLangId.setProgLangId(getIdByLanguage(lang));
            devProgrammingLang.setId(devProgrammingLangId);
            session.merge(devProgrammingLang);
            session.getTransaction().commit();
        }
        session.close();
    }

    @Override
    public void deleteLanguageDeveloper(String id, String language){
        int idByLanguage1 = getIdByLanguage(language);
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DevProgrammingLangId devProgrammingLangId = new DevProgrammingLangId();
        devProgrammingLangId.setDeveloperId(Integer.parseInt(id));
        devProgrammingLangId.setProgLangId(idByLanguage1);
        DevProgrammingLang devProgrammingLang = new DevProgrammingLang();
        devProgrammingLang.setId(devProgrammingLangId);
        session.delete(devProgrammingLang);
        transaction.commit();
        session.close();
    }

    @Override
    public void saveLanguage(ProgrammingLanguage language) {
        SessionFactory sessionFactory = HibernateConnector.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(language);
        transaction.commit();
        session.close();
    }
}
