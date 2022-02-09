package com.zee.zee5app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.EROLE;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository loginRepository;

	@Override
	public String addCredentials(Login login) {
		Login login2 = loginRepository.save(login);
		if (login2 != null) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String deleteCredentials(String userName) throws IdNotFoundException {
		Optional<Login> optional = loginRepository.findById(userName);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No Record Found");
		} else {
			loginRepository.deleteById(userName);
			return "success";
		}
	}

	@Override
	public String getPasswordByUserName(String userName) {
		return null;
	}

	@Override
	public String changeRole(String userName, EROLE erole) {
		return null;
	}

	@Override
	public int changePasswordByUserName(String userName, String password) {
		return loginRepository.changePasswordByUserName(userName, password);
	}

}
