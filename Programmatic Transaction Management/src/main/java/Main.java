import DAO.impl.CarDriverTemplate;
import DAO.tables.CarDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by Andrei on 11/22/2014.
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CarDriverTemplate template = (CarDriverTemplate) context.getBean("carJDBCtemplate");

        List<CarDriver> list = template.getCarsAndTheseDrivers();
        System.out.println();
        for(CarDriver item : list)
            item.print();
        System.out.println();

        //add new car and new driver into database
        //template.insertCarAndDriver("Cristina","Lexus",2010,4600);

    }
}
