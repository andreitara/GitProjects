package md.pharm.hibernate.message;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageMessage {
    private SessionFactory factory;

    public ManageMessage(){
        try{
            //factory = new Configuration().configure().buildSessionFactory();
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Message> getMessages(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Message> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.message.Message").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public Integer addMessage(Message message){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer messageID = null;
        try{
            tx = session.beginTransaction();
            messageID = (Integer) session.save(message);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return messageID;
    }

    public int updateMessage(Message message){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(message);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return message.getId();
    }

    public Message getMessageByID(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        Message message = null;
        try{
            tx = session.beginTransaction();
            message = (Message)session.get(Message.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return message;
    }
}
