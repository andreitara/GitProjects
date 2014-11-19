import org.aspectj.lang.annotation.*;

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

    @Before("execution(public void Person.doAll())")
    public void checkResource() throws InterruptedException {
        System.out.println("Before");
        this.getSemaphore().block();
    }

    @AfterReturning("doAll()")
    public void afterResource(){
        System.out.println("After");
        this.getSemaphore().unblock();
    }
}
