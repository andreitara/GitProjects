import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Created by Andrei on 11/20/2014.
 */

@Configuration
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
    public CarJDBCtemplate carJDBCtemplate(){
        CarJDBCtemplate carJDBCtemplate = new CarJDBCtemplate();
        carJDBCtemplate.setDataSource(dataSource());
        return carJDBCtemplate;
    }
}
