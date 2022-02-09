package com.zee.zee5app.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.zee.zee5pp.utils.PasswordUtils;

@Configuration
// It is used to mark on configuration class/classes.
// Here we will hold all commonly required objects for our application
@ComponentScan("com.zee.zee5app") // Telling the spring from where to scan for objects
@PropertySource("classpath:application.properties") // It is responsible to read the property file for DataBase
public class Config {
	// Configuration : DataBase related, reading properties file, commonly required
	// beans (passwordEncoder)

	// This will bring the already created objects based on the name(reference
	// name)/type
	@Autowired
	Environment environment;
	// This implement object is prepared by Spring
	// We have to inject the already created objects ==> Dependency Injection ==>
	// IOC(Inversion of Control)

	// It is used for providing singleton design pattern on method level
	// If we don't specify the bean name it takes/consider the method name as the
	// bean name
	@Bean(name = "ds")
	// When you need multiple object you can use prototype. We can also use singleton same as bean
	@Scope("prototype")
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setDefaultAutoCommit(false);
		return basicDataSource;
	}
	
	// Rather than making the password util call as component we can also do as done below. It help when we have to do customization
	@Bean
	public PasswordUtils passwordUtils() {
		return new PasswordUtils();
	}
}