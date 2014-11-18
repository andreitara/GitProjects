package events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrei on 11/18/2014.
 */
public class InitiateEvent implements ApplicationListener<ContextStartedEvent>{

    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("Initiate: " + contextStartedEvent.toString());
    }

}
