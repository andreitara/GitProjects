import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andrei on 11/16/2014.
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        HelloSpring hello = (HelloSpring) context.getBean("helloSpring");

        String message = hello.getHelloSpring();

        System.out.println(message);
    }

}
