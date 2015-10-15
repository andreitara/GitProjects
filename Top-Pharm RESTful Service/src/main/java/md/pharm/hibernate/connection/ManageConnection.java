package md.pharm.hibernate.connection;

import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 9/3/2015.
 */
public class ManageConnection {

    private SessionFactory factory;
    private Session session;

    public ManageConnection(){
        factory = HibernateUtil.getSessionFactory();
        session = HibernateUtil.getSession();
    }

    public List<Connection> getConnections(){
        Transaction tx = null;
        List<Connection> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.connection.Connection").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer addConnection(Connection connection){
        Transaction tx = null;
        Integer connectionID = null;
        try{
            tx = session.beginTransaction();
            connectionID = (Integer) session.save(connection);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connectionID;
    }

    public int updateConnection(Connection connection){
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(connection);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connection.getId();
    }

    public boolean deleteConnection(Connection connection){
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(connection);
            tx.commit();
            flag=true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public Connection getConnectionByID(int id){
        Transaction tx = null;
        Connection connection = null;
        try{
            tx = session.beginTransaction();
            connection = (Connection)session.get(Connection.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connection;
    }

    public Connection getConnectionByConnectionKey(String key){
        Transaction tx = null;
        Connection connection = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Connection.class);
            connection = (Connection) criteria.add(Restrictions.eq("connectionKey", key)).uniqueResult();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connection;
    }

}
