package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	private static UserService service;
	public static UserService getInstance() {
		if(service == null)
			service = new UserServiceImpl();
		
		return service;
	}
	
	UserRepository userRepository = UserRepositoryImpl.getInstance();
	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		return this.userRepository.addUser(register);
	}

	@Override
	public String updateUser(String id, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.userRepository.updateUser(id, register);
	}

	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.userRepository.getUserById(id);
	}

	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUsers();
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.userRepository.deleteUserById(id);
	}

	@Override
	public List<Register> getAllUsersDetails() {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUsersDetails();
	}

}
