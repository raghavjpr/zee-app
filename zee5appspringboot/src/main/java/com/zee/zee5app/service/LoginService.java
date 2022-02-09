package com.zee.zee5app.service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.EROLE;
import com.zee.zee5app.exception.IdNotFoundException;

public interface LoginService {

	public String addCredentials(Login login);

	public String deleteCredentials(String userName) throws IdNotFoundException;

	public String getPasswordByUserName(String userName);

	public String changeRole(String userName, EROLE erole);

	int changePasswordByUserName(String userName, String password);

}
