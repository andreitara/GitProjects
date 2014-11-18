import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andrei on 11/18/2014.
 */
public class Main {

    public static void main(String[] args){
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        context.start();
        context.refresh();

        Person andrei = (Person) context.getBean("andrei");
        System.out.println(andrei.getFirstName() + " " + andrei.getLastName() + " : " + andrei.getAge());

        context.close();
        context.stop();
    }
}
