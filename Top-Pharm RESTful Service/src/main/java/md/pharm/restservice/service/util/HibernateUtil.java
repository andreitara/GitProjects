package md.pharm.restservice.service.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Andrei on 10/14/2015.
 */
public class HibernateUtil {

    private static SessionFactory mdFactory;
    private static SessionFactory roFactory;

    public static void buildMDSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure("md.hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            mdFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create MD SessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void buildROSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure("ro.hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            roFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create RO SessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(Country country) {
        switch (country){
            case MD:
                if(mdFactory==null) buildMDSessionFactory();
                return mdFactory;
            case RO:
                if(roFactory==null) buildROSessionFactory();
                return roFactory;
        }
        return null;
    }

    public static Session getSession(Country country){
        SessionFactory factory = getSessionFactory(country);
        Session session = factory.getCurrentSession();
        if(session==null){
            session = factory.openSession();
        }else if(!session.isOpen()){
            session.clear();
            session = factory.openSession();
        }
        return session;
    }

}
