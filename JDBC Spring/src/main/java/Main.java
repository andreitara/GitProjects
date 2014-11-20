import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrei on 11/20/2014.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CarJDBCtemplate carJDBCtemplate = (CarJDBCtemplate) context.getBean("carJDBCtemplate");

        System.out.println("All cars:");
        List<Car> list = carJDBCtemplate.getAllCars();
        for(Car car : list)
            car.print();
        System.out.println();


        System.out.println("Cars with id 2");
        Car car = carJDBCtemplate.getCarByID(2);
        car.print();
        System.out.println();

        carJDBCtemplate.addCar(new Car("Mercedes",2010,3000));

        System.out.println("All cars:");
        list = carJDBCtemplate.getAllCars();
        for(Car item : list)
            item.print();
        System.out.println();

    }

}
