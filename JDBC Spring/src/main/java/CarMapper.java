import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrei on 11/20/2014.
 */
public class CarMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int rowInt) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setEngine(rs.getInt("engine"));
        car.setYear(rs.getInt("year"));
        car.setMark(rs.getString("mark"));

        return car;
    }
}
