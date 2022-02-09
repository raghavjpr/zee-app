package com.zee.zee5app;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;

public class MainSpring {

	public static void main(String[] args) {

		// Establish Spring Environment
		// This will start your spring application
		// Here we have to initialize the application context container(preferred over
		// beans)
		// We will use java based configuration(XML based absolute)

		// Application Context is interface, we will use AbsractApplicationContect which
		// will also implement close();
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		System.out.println(userRepository);
		System.out.println(userRepository.hashCode());

		UserRepository userRepository2 = applicationContext.getBean(UserRepository.class);
		System.out.println(userRepository2);
		System.out.println(userRepository2.hashCode());

		System.out.println(userRepository.equals(userRepository2));
		
		DataSource dataSource = applicationContext.getBean("ds", DataSource.class);
		System.out.println(dataSource);
		System.out.println(dataSource.hashCode());

		DataSource dataSource2 = applicationContext.getBean("ds", DataSource.class);
		System.out.println(dataSource2);
		System.out.println(dataSource2.hashCode());

		System.out.println(dataSource.equals(dataSource2));

		System.out.print("Adding New User rg00000020: ");
		Register register = null;
		try {
			register = new Register("rg00000020", "Raghav", "Gupta", "ragahv.gupta20@zee.com", "raghav123",
					new BigDecimal("7976196211"));
			String result = userRepository.addUser(register);
			System.out.println(result);
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
			e.printStackTrace();
		}

		applicationContext.close();

	}

}
