package NotificationApp.NotificationConfig;

import NotificationApp.NotificationModel.NotificateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
public class NotificationConfig {

    @Value("${spring.datasource.driver}")
    private  String DRIVER;
    @Value("${spring.datasource.url}")
    private  String URL;
    @Value("${spring.datasource.username}")
    private  String NAME;
    @Value("${spring.datasource.password}")
    private  String PASSWORD;
    @Value("${spring.mail.mailfrom}")
    private String MAILFROM;
    @Value("${spring.mail.frompass}")
    private String FROMPASS;



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(NAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired Properties properties, @Autowired DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        containerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        containerEntityManagerFactoryBean.setDataSource(dataSource);
        containerEntityManagerFactoryBean.setPersistenceUnitName("jpa-unit");
        containerEntityManagerFactoryBean.setJpaProperties(properties);
        containerEntityManagerFactoryBean.setPackagesToScan(NotificateRequest.class.getPackage().getName());

        return containerEntityManagerFactoryBean;
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hbm2ddl.auto", "none");
        properties.setProperty("current_session_context_class", "thread");
        properties.setProperty("connection.pool_size", "20");
        properties.setProperty("hibernate.dbcp.initialSize", "5");
        properties.setProperty("hibernate.dbcp.maxTotal", "20");
        properties.setProperty("hibernate.dbcp.maxIdle", "10");
        properties.setProperty("hibernate.dbcp.minIdle", "5");
        properties.setProperty("hibernate.dbcp.maxWaitMillis", "-1");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }

    @Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(MAILFROM);
        mailSender.setPassword(FROMPASS);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("somebody@gmail.com");
        message.setFrom("admin@gmail.com");
        message.setText("FATAL - Application crash. Save your job !!");
        return message;
    }
}
