import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Andrei on 11/20/2014.
 */
public class CarJDBCtemplate implements CarsDAO {
    DataSource dataSource;
    JdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public Car getCarByID(int id) {
        String sql = "Select * From [SpringTest].[dbo].[Cars] Where ID=?";
        Car car = template.queryForObject(sql, new Object[]{id}, new CarMapper());
        return car;
    }

    @Override
    public void addCar(Car car) {
        String sql = "INSERT INTO [SpringTest].[dbo].[Cars](mark,year,engine) values (?,?,?)";
        int exitCode = template.update(sql, car.getMark(), car.getYear(), car.getEngine());
        System.out.println("Add car: " + car.getMark() + " " + car.getYear() + " " + car.getEngine() +" ;  ExitCode = " + exitCode );
    }

    @Override
    public Car getCarByNameAndAge(String name, int age) {
        String sql = "SELECT * FROM [SpringTest].[dbo].[Cars] WHERE name=? AND age=?";
        Car car = template.queryForObject(sql, new Object[]{name, age}, new CarMapper());
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        String sql = "SELECT * FROM [SpringTest].[dbo].[Cars]";
        List<Car> listCars = template.query(sql, new CarMapper());
        return listCars;
    }
}
