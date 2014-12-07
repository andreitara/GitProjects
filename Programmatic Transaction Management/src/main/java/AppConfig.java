import DAO.aspect.AspectCarDriver;
import DAO.impl.AspectCarDriverTemplate;
import DAO.impl.CarDriverTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by Andrei on 11/22/2014.
 */

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setUsername("sa");
        driver.setPassword("manager");
        driver.setUrl("jdbc:jtds:sqlserver://localhost");
        driver.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");

        return driver;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }


    //***********************************************
    //Without Aspect
    //***********************************************
    @Bean
    public CarDriverTemplate carJDBCtemplate(){
        CarDriverTemplate carDriverTemplate = new CarDriverTemplate();
        carDriverTemplate.setDataSource(dataSource());
        carDriverTemplate.setPlatformTransactionManager(dataSourceTransactionManager());
        return carDriverTemplate;
    }


    //***********************************************
    //With Aspect
    //***********************************************
    @Bean
    public AspectCarDriver aspectCarDriver(){
        AspectCarDriver aspectCarDriver = new AspectCarDriver();
        aspectCarDriver.setPlatformTransactionManager(dataSourceTransactionManager());
        return  aspectCarDriver;
    }

    @Bean
    public AspectCarDriverTemplate aspectCarDriverTemplate(){
        AspectCarDriverTemplate aspectCarDriverTemplate = new AspectCarDriverTemplate();
        aspectCarDriverTemplate.setDataSource(dataSource());
        return aspectCarDriverTemplate;
    }

}
