import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Andrei on 11/19/2014.
 */
@Aspect
public class Person {

    private String name;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doSomething(){
        System.out.println(this.name + " do this!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doAll(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1 ; i<10; i++){
                    doSomething();
                }
            }
        }).start();
    }

}
