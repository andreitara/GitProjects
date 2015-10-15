package md.pharm.hibernate.user.permission;

import md.TopPharmResTfulServiceApplication;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 9/28/2015.
 */
public class ManagePermission {
    private SessionFactory factory;
    private Session session;

    public ManagePermission(){
        factory = HibernateUtil.getSessionFactory();
        session = HibernateUtil.getSession();
    }

    public List<Permission> getPermissions(){
        Transaction tx = null;
        List<Permission> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.user.permission.Permission").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer addPermission(Permission permission){
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(permission);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return id;
    }

    public int updatePermission(Permission permission){
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(permission);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return permission.getId();
    }

    public void deletePermission(Permission permission){
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(permission);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
    }

}
