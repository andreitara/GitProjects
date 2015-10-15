package md.pharm.restservice.service.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Andrei on 10/14/2015.
 */
public class HibernateUtil {

    private static SessionFactory factory;

    public static void buildSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(factory==null) buildSessionFactory();
        return factory;
    }

    public static Session getSession(){
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
