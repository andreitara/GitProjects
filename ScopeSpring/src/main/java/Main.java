import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andrei on 11/16/2014.
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Test singleton = (Test) context.getBean("singleton");
        singleton.setNumber(3);
        System.out.println(singleton.getText() + " with number = " + singleton.getNumber());

        Test singleton2 = (Test) context.getBean("singleton");
        System.out.println(singleton2.getText() + " with number = " + singleton2.getNumber());

        Test prototype = (Test) context.getBean("prototype");
        System.out.println(prototype.getText() + " with number = " + prototype.getNumber());

    }
}
