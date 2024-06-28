package com.maven.configruation;

import com.maven.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.maven.services.UserService;
import com.maven.services.impl.UserServiceImpl;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.maven")
public class SpringConfig {
    @Bean
    public DriverManagerDataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public Properties getProperty(){
        Properties p = new Properties();
        p.setProperty("hibernate.hbm2ddl.auto", "update");
        p.setProperty("hibernate.show_sql", "true");
        p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return p;
    }


    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(getDataSource());
        sf.setHibernateProperties(getProperty());
        sf.setAnnotatedClasses(User.class);
        return sf;
    }


    @Bean
    public HibernateTemplate getHibernateTemplate(){
        HibernateTemplate ht = new HibernateTemplate();
        ht.setSessionFactory(getSessionFactory().getObject());
        return ht;
    }

    @Bean("userService")
    public UserService getUserService(){
        UserServiceImpl usi = new UserServiceImpl();
        usi.setHibernateTemplate(getHibernateTemplate());
        return usi;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(getSessionFactory().getObject());
        return htm;
    }
}
