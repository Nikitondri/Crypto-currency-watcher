package com.idf.crypto_currency_watcher.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan("com.idf.crypto_currency_watcher*")
@EnableJpaRepositories(basePackages = "com.idf.crypto_currency_watcher.repository*")
@EnableTransactionManagement
@PropertySource(
        {"classpath:properties/db.properties",
        "classpath:properties/session-factory.properties"}
)
@EnableWebMvc
public class Config implements WebMvcConfigurer {

    @Value("${driverClass}")
    private String driverClass;

    @Value("${dbuser}")
    private String user;

    @Value("${password}")
    private String password;

    @Value("${jdbcUrl}")
    private String jdbcUrl;
    private Environment env;

    public Config() {
    }

    @Autowired
    public Config(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverClass);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(user);
            dataSource.setPassword(password);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        factory.setPackagesToScan(env.getRequiredProperty("packagesToScan"));
        Properties props = new Properties();
        props.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        props.put("hibernate.ddl-auto", env.getRequiredProperty("hibernate.ddl-auto"));
        props.put("spring.session.jdbc.initialize-schema", env.getRequiredProperty("spring.session.jdbc.initialize-schema"));
        factory.setJpaProperties(props);
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
}
