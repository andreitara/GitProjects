package DAO.mapper;

import DAO.tables.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrei on 11/23/2014.
 */
public class CarMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int rowInt) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("ID"));
        car.setEngine(rs.getInt("ENGINE"));
        car.setYear(rs.getInt("YEAR"));
        car.setMark(rs.getString("MARK"));

        return car;
    }
}
