package com.zee.zee5app.service.impl;

import java.io.IOException;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.impl.LoginRepositoryImpl;
import com.zee.zee5app.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginServiceImpl() throws IOException {
		// TODO Auto-generated constructor stub
		loginRepository = LoginRepositoryImpl.getInstance();
	}

	private static LoginService loginService = null;

	public static LoginService getInstance() throws IOException {
		if (loginService == null)
			loginService = new LoginServiceImpl();
		return loginService;
	}
	
	LoginRepository loginRepository = null;

	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		return this.loginRepository.addCredentials(login);
	}

	@Override
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		return this.loginRepository.deleteCredentials(userName);
	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return this.loginRepository.changePassword(userName, password);
	}

	@Override
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		return this.loginRepository.changeRole(userName, role);
	}

}
