package DAO.impl;

import DAO.CarDriverDAO;
import DAO.mapper.CarDriverMapper;
import DAO.tables.CarDriver;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Andrei on 11/22/2014.
 */
public class CarDriverTemplate implements CarDriverDAO {

    private DataSource dataSource;
    private JdbcTemplate template;
    private PlatformTransactionManager platformTransactionManager;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        template = new JdbcTemplate(dataSource);
    }

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager){
        this.platformTransactionManager = platformTransactionManager;
    }

    @Override
    public List<CarDriver> getCarsAndTheseDrivers() {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        try{
            String selectCarDriver = "SELECT Driver.Name as NameDriver, Cars.Mark as NameCar, Cars.Year as YearCar FROM [SpringTest].[dbo].[Cars] as  Cars, [SpringTest].[dbo].[Driver] as Driver WHERE CARS.ID=Driver.ID";
            List<CarDriver> listCarDriver = template.query(selectCarDriver, new CarDriverMapper());
            platformTransactionManager.commit(status);
            return listCarDriver;
        }catch (DataAccessException e){
            System.out.println("Error into Select cars and drivers transaction");
            platformTransactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public void insertCarAndDriver(String nameDriver, String nameCar, int yearCar, int engineCar) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        try{
            String insertCar = "INSERT INTO [SpringTest].[dbo].[Cars](mark, year, engine) VALUES (?,?,?)";
            template.update(insertCar, nameCar, yearCar, engineCar);

            String selectIDCar = "SELECT ID FROM [SpringTest].[dbo].[Cars] WHERE mark=? AND year=? AND engine=?";
            int carID = template.queryForInt(selectIDCar, new Object[]{nameCar, yearCar, engineCar});

            String insertDriver = "INSERT INTO [SpringTest].[dbo].[Driver](CarID, Name) VALUES(?,?)";
            template.update(insertDriver, carID, nameDriver);

            platformTransactionManager.commit(status);

        }catch (DataAccessException e){
            System.out.println("Error into add car and driver transaction");
            platformTransactionManager.rollback(status);
            throw e;
        }
    }
}
