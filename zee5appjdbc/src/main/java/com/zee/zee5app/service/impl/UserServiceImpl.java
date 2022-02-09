package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

public class UserServiceImpl implements UserService {

	UserRepository userRepository = null;

	private UserServiceImpl() throws IOException {
		// TODO Auto-generated constructor stub
		userRepository = UserRepositoryImpl.getInstance();
	}

	private static UserService service;

	public static UserService getInstance() throws IOException {
		if (service == null)
			service = new UserServiceImpl();

		return service;
	}

	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		return this.userRepository.addUser(register);
	}

	@Override
	public String updateUser(String id, Register register) {
		// TODO Auto-generated method stub
		return this.userRepository.updateUser(id, register);
	}

	@Override
	public Optional<Register> getUserById(String id) {
		// TODO Auto-generated method stub
		return this.userRepository.getUserById(id);
	}

	@Override
	public List<Register> getAllUsersDetails()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUsersDetails();
	}

	@Override
	public Optional<List<Register>> getAllUsersDetailsThroughOptional()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUsersDetailsThroughOptional();
	}

	@Override
	public String deleteUserById(String id) {
		// TODO Auto-generated method stub
		return this.userRepository.deleteUserById(id);
	}

	@Override
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUsers();
	}
}
