package com.zee.zee5app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.service.LoginService;

public class MainLogin {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		LoginService loginService = applicationContext.getBean(LoginService.class);

		System.out.print("Changing User role of user ragahv.gupta01@zee.com: ");
		String resultRoleChange = loginService.changeRole("ragahv.gupta01@zee.com", ROLE.ROLE_ADMIN);
		System.out.println(resultRoleChange);

		applicationContext.close();

	}

}
