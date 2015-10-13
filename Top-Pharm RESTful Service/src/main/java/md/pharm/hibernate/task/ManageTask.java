package md.pharm.hibernate.task;

import md.TopPharmResTfulServiceApplication;
import md.pharm.hibernate.doctor.Doctor;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageTask {
    private SessionFactory factory;

    public ManageTask(){
        /*
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
        */
        factory = TopPharmResTfulServiceApplication.factory;
    }

    public List<Task> getTasks(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Task> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.task.Task").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public Integer addTask(Task task){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer taskID = null;
        try{
            tx = session.beginTransaction();
            taskID = (Integer) session.save(task);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return taskID;
    }

    public boolean updateTask(Task task){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.update(task);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            flag = false;
        }finally {
            session.close();
        }
        return flag;
    }

    public Task getTaskByID(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        Task task = null;
        try{
            tx = session.beginTransaction();
            task = (Task)session.get(Task.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return task;
    }

    public boolean deleteTask(Task task){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(task);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            flag = false;
        }finally {
            session.close();
        }
        return flag;
    }

    public boolean deleteDoctorTask(Integer taskID, Integer doctorID){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete [TopPharm].[dbo].[DoctorTask] where taskID = " + taskID + " and doctorID = " + doctorID);
            int result = query.executeUpdate();
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            flag = false;
        }finally {
            session.close();
        }
        return flag;
    }

    public boolean deleteProductTask(Integer taskID, Integer productID){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete [TopPharm].[dbo].[ProductTask] where taskID = " + taskID + " and productID = " + productID);
            int result = query.executeUpdate();
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            flag = false;
        }finally {
            session.close();
        }
        return flag;
    }

    public boolean deleteUserTask(Integer taskID, Integer userID){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete [TopPharm].[dbo].[UserTask] where taskID = " + taskID + " and userID = " + userID);
            int result = query.executeUpdate();
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            flag = false;
        }finally {
            session.close();
        }
        return flag;
    }


}
