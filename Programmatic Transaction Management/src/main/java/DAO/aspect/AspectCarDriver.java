package DAO.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by Andrei on 11/23/2014.
 */

@Aspect
public class AspectCarDriver {

    private PlatformTransactionManager platformTransactionManager;
    private TransactionStatus status;

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager){
        this.platformTransactionManager = platformTransactionManager;
    }

    @Pointcut("execution(* DAO.impl.AspectCarDriverTemplate.*(..)) && !execution(* DAO.impl.AspectCarDriverTemplate.setDataSource(..))")
    public void pointCutCarDriverTemplate(){}

    @Before("pointCutCarDriverTemplate()")
    public void setStatusBeforeTransaction(){
        TransactionDefinition def = new DefaultTransactionDefinition();
        status = platformTransactionManager.getTransaction(def);
        System.out.println("Set status before transactions: " + status.toString());
    }

    @AfterReturning("pointCutCarDriverTemplate()")
    public void commitTransaction(){
        platformTransactionManager.commit(status);
        System.out.println("Commit successfully");
    }

    @AfterThrowing(value = "pointCutCarDriverTemplate()", throwing = "e")
    public void rollbackTransaction(Throwable e) throws Throwable {
        platformTransactionManager.rollback(status);
        System.out.println("Error into Select cars and drivers transaction");
        throw e;
    }

}
