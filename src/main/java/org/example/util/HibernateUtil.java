package org.example.util;
import org.example.entity.Certificate;
import org.example.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
public class HibernateUtil {
  private static final SessionFactory sessionFactory;

  static {
    try {
      // Create configuration instance
      Configuration configuration = new Configuration();
      // Load hibernate.cfg.xml
      configuration.configure("hibernate.cfg.xml");

      // Register your annotated entity
      configuration.addAnnotatedClass(Student.class);
      configuration.addAnnotatedClass(Certificate.class);

      // Create service registry
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .applySettings(configuration.getProperties()).build();

      // Build session factory
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      System.out.println("✅ Hibernate SessionFactory created successfully!");


    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  // Getter for sessionFactory
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  // Close session factory (useful during shutdown)
  public static void shutdown() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }

}
