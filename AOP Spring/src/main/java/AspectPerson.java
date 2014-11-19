import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Andrei on 11/19/2014.
 */

@Aspect
public class AspectPerson {
    private MySemaphore semaphore;

    public AspectPerson() {
    }

    public MySemaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(MySemaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Pointcut(value = "execution(* Person.doAll(..))")
    public void doAll() {}

    @Before(value = "doAll()")
    public void checkResource() throws InterruptedException {
        System.out.println("Before");
        this.getSemaphore().block();
    }

    @After(value = "doAll()")
    public void afterResource(){
        System.out.println("After");
        this.getSemaphore().unblock();
    }
}
