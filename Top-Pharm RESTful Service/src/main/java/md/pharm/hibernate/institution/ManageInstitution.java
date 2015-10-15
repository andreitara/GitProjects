package md.pharm.hibernate.institution;

import md.TopPharmResTfulServiceApplication;
import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.util.HibernateUtil;
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
    private Session session;

    public ManageInstitution(){
        factory = HibernateUtil.getSessionFactory();
        session = HibernateUtil.getSession();
    }

    public List<Institution> getInstitutions(){
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
        }
        return list;
    }

    public Integer addInstitution(Institution institution){
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
        }
        return institutionID;
    }

    public Integer addInstitutionAddress(Address address){
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(address);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return id;
    }

    public boolean updateInstitution(Institution institution){
        boolean flag = false;
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
        }
        return flag;
    }

    public boolean updateAddress(Address address){
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(address);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public Institution getInstitutionByID(int id){
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
        }
        return institution;
    }

    public Address getInstitutionAddressByInstitutionID(int id){
        Transaction tx = null;
        Institution institution;
        Address address = null;
        boolean flag = true;
        try{
            tx = session.beginTransaction();
            institution = (Institution)session.get(Institution.class, id);
            if(institution!=null) address = institution.getAddress();
            tx.commit();
            flag = false;
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            flag = true;
        }finally {
        }
        if(flag) return null;
        return address;
    }

    public Institution getInstitutionByLongName(String longName){
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
        }
        return institution;
    }

    public boolean deleteInstitution(Institution institution){
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
        }
        return flag;
    }

}
