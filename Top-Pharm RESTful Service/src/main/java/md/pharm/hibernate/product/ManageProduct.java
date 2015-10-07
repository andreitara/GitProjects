package md.pharm.hibernate.product;

import md.pharm.hibernate.doctor.Doctor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/4/2015.
 */
public class ManageProduct {

    private SessionFactory factory;

    public ManageProduct(){
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

    public List<Product> getProducts(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Product> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.product.Product").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public Integer addProduct(Product product){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(product);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return id;
    }

    public Integer addProductObjective(Objective objective){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(objective);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return id;
    }

    public boolean updateProduct(Product product){
        boolean flag = false;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(product);
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

    public boolean updateObjective(Objective objective){
        boolean flag = false;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(objective);
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

    public Product getProductByID(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        Product product = null;
        try{
            tx = session.beginTransaction();
            product = (Product)session.get(Product.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return product;
    }

    public Objective getObjectiveByID(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        Objective objective = null;
        try{
            tx = session.beginTransaction();
            objective = (Objective)session.get(Objective.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return objective;
    }

    public Product getProductByObjectiveID(int objectiveID){
        Session session = factory.openSession();
        Transaction tx = null;
        Objective objective = null;
        Product product = null;
        try{
            tx = session.beginTransaction();
            objective = (Objective)session.get(Objective.class, objectiveID);
            if(objective != null) product = objective.getProduct();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return product;
    }

    public Set<Objective> getObjectivesByProductID(int productID){
        Session session = factory.openSession();
        Transaction tx = null;
        Product product = null;
        Set<Objective> list = null;
        try{
            tx = session.beginTransaction();
            product = (Product)session.get(Product.class, productID);
            list = product.getObjectives();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public boolean delete(Product product){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(product);
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

    public boolean deleteObjective(Objective objective){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(objective);
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
