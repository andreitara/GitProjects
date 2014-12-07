package DAO.impl;

import DAO.CarDriverDAO;
import DAO.mapper.CarDriverMapper;
import DAO.mapper.CarMapper;
import DAO.tables.Car;
import DAO.tables.CarDriver;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import javax.sql.RowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrei on 11/23/2014.
 */
public class AspectCarDriverTemplate implements CarDriverDAO{

    private DataSource dataSource;
    private JdbcTemplate template;

    public AspectCarDriverTemplate() {
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        template = new JdbcTemplate(this.dataSource);
    }

    @Override
    public List<CarDriver> getCarsAndTheseDrivers() {
        String selectCarDriver = "SELECT Driver.Name as NameDriver, Cars.Mark as NameCar, Cars.Year as YearCar FROM [SpringTest].[dbo].[Cars] as  Cars, [SpringTest].[dbo].[Driver] as Driver WHERE CARS.ID=Driver.CarID";
        List<CarDriver> listCarDriver = template.query(selectCarDriver, new CarDriverMapper());

        return listCarDriver;
    }

    @Override
    public void insertCarAndDriver(String nameDriver, String nameCar, int yearCar, int engineCar) throws SQLException {
        String insertCar = "INSERT INTO [SpringTest].[dbo].[Cars](mark, year, engine) VALUES (?,?,?)";
        template.update(insertCar, nameCar, yearCar, engineCar);

        String selectIDCar = "SELECT * FROM [SpringTest].[dbo].[Cars] AS Cars WHERE Cars.MARK=? AND Cars.YEAR=? AND Cars.ENGINE=?";
        Car car = template.queryForObject(selectIDCar, new Object[]{nameCar, yearCar, engineCar}, new CarMapper());

        String insertDriver = "INSERT INTO [SpringTest].[dbo].[Driver](CarID, Name) VALUES(?,?)";
        template.update(insertDriver, car.getId(), nameDriver);

        return;
    }

}
