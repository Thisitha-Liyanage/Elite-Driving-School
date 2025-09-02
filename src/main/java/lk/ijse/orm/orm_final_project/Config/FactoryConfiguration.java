package lk.ijse.orm.orm_final_project.Config;

import lk.ijse.orm.orm_final_project.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            Properties properties = new Properties();
            InputStream propStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            if (propStream == null) {
                throw new RuntimeException("hibernate.properties file not found!");
            }
            properties.load(propStream);

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);


            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Lesson.class);
            configuration.addAnnotatedClass(Instructor.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Enrollment.class);
            configuration.addAnnotatedClass(Register.class);

            sessionFactory = configuration.buildSessionFactory();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create SessionFactory", e);
        }
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
