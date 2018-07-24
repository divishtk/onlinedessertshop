package com.dessert.onlinedessertbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages= {"com.dessert.onlinnedessertbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig 

{

	//change the below based on the DBMS you choose
	private final static String DATABASE_URl= "jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DATABASE_DRIVER="org.h2.Driver";
	private final static String DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
	private final static String DATABASE_USERNAME="sa";
	private final static String DATABASE_PASSWORD="";
	
	
	
	//dataSource bean will be available
	@Bean
	public DataSource getDataSource()
	{
		BasicDataSource dataSource =new BasicDataSource();
		
		//Providing Database connection information
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URl);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		
		return dataSource;
	}
	
	//session factory bean will be avilable
	@Bean
	
	public SessionFactory getSessionFactory (DataSource dataSource)
	{
		LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.dessert.onlinnedessertbackend.dto");
		
		return builder.buildSessionFactory();
		
		
		
	}

	
	//All the hibernate properties will be returned
	private Properties getHibernateProperties() 

	{
		Properties properties=new Properties();
		
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		
		
		
		
		
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	
	
	//TrascationManager Bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory  sessionFactory)
	{
		HibernateTransactionManager transcationManager=new HibernateTransactionManager(sessionFactory);
		return transcationManager;
		
		
		
	}
	
	
	
	
}
