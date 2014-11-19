import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by Andrei on 11/16/2014.
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Person cristina = (Person) context.getBean("andrei");

        System.out.println(cristina.getFirtName() + " " + cristina.getLastName() + " " + cristina.getAge());

    }

}
