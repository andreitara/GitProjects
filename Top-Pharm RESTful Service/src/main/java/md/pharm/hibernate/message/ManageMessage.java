package md.pharm.hibernate.message;

import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageMessage {
    private SessionFactory factory;
    private Session session;

    public ManageMessage(){
        factory = HibernateUtil.getSessionFactory();
        session = HibernateUtil.getSession();
    }

    public List<Message> getMessages(){
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
        }
        return list;
    }

    public Integer addMessage(Message message){
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
        }
        return messageID;
    }

    public int updateMessage(Message message){
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(message);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return message.getId();
    }

    public Message getMessageByID(int id){
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
        }
        return message;
    }
}
