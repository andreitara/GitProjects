import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andrei on 11/16/2014.
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Calculator calculator = (Calculator) context.getBean("calculator");
        System.out.println("Name:       " + calculator.getName());
        System.out.println("Processor:  " + calculator.getProcessor().getSpeed());
        System.out.println("Ram:        " + calculator.getRam().getSize());
        System.out.println("Ecran:      " + calculator.getEcran().getDiagonal());
        System.out.println("HardDisk:   " + calculator.getHardDisk().getSize());

        System.out.println();

        Calculator calculator2 = (Calculator) context.getBean("calculator2");
        System.out.println("Name:       " + calculator2.getName());
        System.out.println("Processor:  " + calculator2.getProcessor().getSpeed());
        System.out.println("Ram:        " + calculator2.getRam().getSize());
        System.out.println("Ecran:      " + calculator2.getEcran().getDiagonal());
        System.out.println("HardDisk:   " + calculator2.getHardDisk().getSize());

    }

}
