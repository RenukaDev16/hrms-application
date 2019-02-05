package com.revature.hrms.common;

import java.util.Properties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.revature.hrms.models")
@EnableTransactionManagement
public class SessionFactoryBean {

  @Autowired DatabaseProperties blogProperties;

  @Autowired DataSource dataSource;

  @Autowired
  @Bean(name = "sessionFactory")
  public SessionFactory getSessionFactory() {
    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    sessionBuilder.scanPackages("com.revature.hrms.models");
    sessionBuilder.addProperties(getHibernateProperties());
    return sessionBuilder.buildSessionFactory();
  }

  @Autowired
  @Bean(name = "transactionManager")
  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager transactionManager =
        new HibernateTransactionManager(sessionFactory);
    return transactionManager;
  }

  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", blogProperties.getHibernateDialect());
    properties.put("hibernate.default_schema", blogProperties.getHibernateDefaultSchema());
    properties.put("hibernate.show_sql", blogProperties.getHibernateShowSQL());
    properties.put("hibernate.format_sql", blogProperties.getHibernateFormatSQL());
    properties.put("hibernate.use_sql_comments", blogProperties.getHibernateUseSQLComments());
    properties.put(
        "hibernate.generate_statistics", blogProperties.getHibernateGenerateStatistics());
    properties.put("hibernate.connection.autocommit", blogProperties.getHibernateAutoCommit());
    return properties;
  }
}
