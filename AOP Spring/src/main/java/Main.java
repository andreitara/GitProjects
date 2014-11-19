import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Andrei on 11/19/2014.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Person andrei = (Person) context.getBean("andrei");
        andrei.doAll();

        Thread.sleep(4000);

        Person grigore = (Person) context.getBean("grigore");
        grigore.doAll();
    }
}
