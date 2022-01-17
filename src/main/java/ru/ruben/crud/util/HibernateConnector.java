package ru.ruben.crud.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConnector {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            StandardServiceRegistry configure = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata build = new MetadataSources(configure).getMetadataBuilder().build();
            sessionFactory = build.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }

}
