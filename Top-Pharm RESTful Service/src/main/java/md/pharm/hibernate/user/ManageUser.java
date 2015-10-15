package md.pharm.hibernate.user;

import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.connection.ManageConnection;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Andrei on 9/3/2015.
 */
public class ManageUser {

    private SessionFactory factory;
    private Session session;

    public ManageUser(){
        factory = HibernateUtil.getSessionFactory();
        session = HibernateUtil.getSession();
    }

    public List<User> getUsers(){
        Transaction tx = null;
        List<User> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.user.User").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer addUser(User user){
        Transaction tx = null;
        Integer userID = null;
        try{
            tx = session.beginTransaction();
            userID = (Integer) session.save(user);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            //session.close();
        }
        return userID;
    }

    public boolean updateUser(User user){
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            //session.close();
        }
        return flag;
    }

    public boolean deleteUser(User user){
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            //session.close();
        }
        return flag;
    }

    public User getUserByID(int id){
        Transaction tx = null;
        User user = null;
        try{
            tx = session.beginTransaction();
            user = (User)session.get(User.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            //session.close();
        }
        return user;
    }

    public User getUserByUsername(String username){
        Transaction tx = null;
        User user = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.eq("username",username)).uniqueResult();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            //session.close();
        }
        return user;
    }

    public User getUserByConnectionKey(String connectionKey){
        User user = null;
        ManageConnection manageConnection = new ManageConnection();
        Connection connection = manageConnection.getConnectionByConnectionKey(connectionKey);
        if(connection!=null) {
            user = connection.getUser();
        }
        return user;
    }




}
