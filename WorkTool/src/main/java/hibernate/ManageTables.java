package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Andrei on 6/23/2015.
 */
public class ManageTables {
    private SessionFactory factory;

    public ManageTables() {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<TradeRelationship> getTradeRelationships(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<TradeRelationship> cars = null;
        try{
            tx = session.beginTransaction();
            cars = session.createQuery("FROM hibernate.TradeRelationship").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return cars;
    }

}
