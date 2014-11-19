import java.util.concurrent.Semaphore;

/**
 * Created by Andrei on 11/19/2014.
 */
public class MySemaphore {

    private Semaphore semaphore;

    public MySemaphore(int i) {
        semaphore = new Semaphore(1);
    }

    public void block() throws InterruptedException {
        semaphore.acquire();
    }

    public void unblock(){
        semaphore.release(1);
    }

}
