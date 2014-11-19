import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Andrei on 11/19/2014.
 */
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Person andrei(){
        Person person = new Person();
        person.setName("Andrei");
        return person;
    }

    @Bean
    public Person grigore(){
        Person person = new Person();
        person.setName("Grigore");
        return person;
    }

    @Bean
    public MySemaphore semaphore(){
        return new MySemaphore(1);
    }

    @Bean
    public AspectPerson aspectPerson(){
        AspectPerson aspectPerson = new AspectPerson();
        aspectPerson.setSemaphore(semaphore());
        return  aspectPerson;
    }
}
