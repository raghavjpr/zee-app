package com.zee.zee5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.service.LoginService;

@SpringBootApplication
public class MainLogin {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringbootApplication.class, args);

		LoginService loginService = applicationContext.getBean(LoginService.class);

		try {
			loginService.deleteCredentials("log0005");
		} catch (IdNotFoundException e1) {
			e1.printStackTrace();
		}

		int i = loginService.changePasswordByUserName("rg0035", "test123");
		if (i != 0) {
			System.out.println("Password changed!");
		} else {
			System.out.println("There is some problem");

		}

		applicationContext.close();
	}

}
