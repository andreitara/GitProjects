package DAO;

import DAO.tables.CarDriver;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Andrei on 11/22/2014.
 */
public interface CarDriverDAO {

    public void setDataSource(DataSource dataSource);
    
    public void insertCarAndDriver(String nameDriver, String nameCar, int yearCar, int engineCar);

    public List<CarDriver> getCarsAndTheseDrivers();

}
