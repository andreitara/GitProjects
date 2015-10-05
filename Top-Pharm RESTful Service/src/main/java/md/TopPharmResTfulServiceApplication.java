package md;

import md.pharm.hibernate.connection.ManageConnection;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.message.ManageMessage;
import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.training.ManageTraining;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.permission.ManagePermission;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Filter;

@Configuration
@SpringBootApplication
public class TopPharmResTfulServiceApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        //ManageInstitution manageInstitution = new ManageInstitution();
        SpringApplication.run(TopPharmResTfulServiceApplication.class, args);
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
        handlerMapping.setUseSuffixPatternMatch(false);
        handlerMapping.setUseTrailingSlashMatch(false);
        return handlerMapping;
    }
}
