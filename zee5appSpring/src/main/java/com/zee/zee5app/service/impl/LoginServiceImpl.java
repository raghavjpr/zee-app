package com.zee.zee5app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository loginRepository;

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

	@Override
	public String getPasswordByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
