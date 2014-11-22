package DAO.mapper;

import DAO.tables.CarDriver;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrei on 11/22/2014.
 */
public class CarDriverMapper implements RowMapper<CarDriver>{


    @Override
    public CarDriver mapRow(ResultSet resultSet, int i) throws SQLException {

        CarDriver carDriver = new CarDriver();
        carDriver.setNameCar(resultSet.getString("NameCar"));
        carDriver.setNameDriver(resultSet.getString("NameDriver"));
        carDriver.setYearCar(resultSet.getInt("YearCar"));

        return carDriver;
    }
}
