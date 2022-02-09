package com.zee.zee5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainSubscription {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringbootApplication.class, args);

		applicationContext.close();
	}

}
