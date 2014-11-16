import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andrei on 11/16/2014.
 */
public class Main {

    public static void main(String[] args){

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml"); //AbstractApplicationContext contains registerShutdownHook method
        context.registerShutdownHook(); //Need for call destroy method

        TestInterface testInterface = (TestInterface) context.getBean("interface");
        testInterface.show(3);

        TestBean testBean= (TestBean) context.getBean("bean");
        testBean.show(3);
    }

}
