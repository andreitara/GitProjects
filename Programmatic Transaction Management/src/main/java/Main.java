import DAO.CarDriverDAO;
import DAO.impl.AspectCarDriverTemplate;
import DAO.impl.CarDriverTemplate;
import DAO.tables.CarDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrei on 11/22/2014.
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Without Aspect
        //CarDriverTemplate template = (CarDriverTemplate) context.getBean("carJDBCtemplate");

        //With Aspect
        CarDriverDAO template = (CarDriverDAO) context.getBean("aspectCarDriverTemplate");

        List<CarDriver> list = template.getCarsAndTheseDrivers();
        System.out.println();
        for(CarDriver item : list)
            item.print();
        System.out.println();

        //add new car and new driver into database
        //template.insertCarAndDriver("Ion","Ferrari",2014,5000);

    }
}
