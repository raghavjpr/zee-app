package com.zee.zee5app;

import java.io.IOException;

import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.impl.LoginServiceImpl;

public class MainLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginService loginService = null;
		try {
			loginService = LoginServiceImpl.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print("Changing User role of user ragahv.gupta01@zee.com: ");
		String resultRoleChange = loginService.changeRole("ragahv.gupta01@zee.com", ROLE.ROLE_ADMIN);
		System.out.println(resultRoleChange);

	}

}
