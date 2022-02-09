package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository = null;

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
	public Optional<Register> getUserById(String id) throws InvalidNameException, InvalidEmailException, InvalidIdLengthException {
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
