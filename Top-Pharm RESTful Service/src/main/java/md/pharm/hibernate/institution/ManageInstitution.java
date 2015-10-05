package md.pharm.hibernate.institution;

import md.pharm.hibernate.user.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 9/4/2015.
 */
public class ManageInstitution {

    private SessionFactory factory;

    public ManageInstitution(){
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

    public List<Institution> getInstitutions(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Institution> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.institution.Institution").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public Integer addInstitution(Institution institution){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer institutionID = null;
        try{
            tx = session.beginTransaction();
            institutionID = (Integer) session.save(institution);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return institutionID;
    }

    public boolean updateInstitution(Institution institution){
        boolean flag = false;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(institution);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return flag;
    }

    public Institution getInstitutionByID(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        Institution institution = null;
        try{
            tx = session.beginTransaction();
            institution = (Institution)session.get(Institution.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return institution;
    }

    public Institution getInstitutionByLongName(String longName){
        Session session = factory.openSession();
        Transaction tx = null;
        Institution institution = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Institution.class);
            institution = (Institution)criteria.add(Restrictions.eq("longName", longName)).uniqueResult();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return institution;
    }

    public boolean deleteInstitution(Institution institution){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(institution);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return flag;
    }

}
