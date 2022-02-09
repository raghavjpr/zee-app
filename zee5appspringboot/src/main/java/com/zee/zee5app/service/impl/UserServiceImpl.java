package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginService loginService;

	@Autowired
	LoginRepository loginRepository;

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Register addUser(Register register) throws AlreadyExistsException {
		Optional<Register> optional = userRepository.findById(register.getRegisterId());
		if (optional.isPresent()) {
			throw new AlreadyExistsException("Record with " + register.getRegisterId() + " register id present in Register Table!");
		}
		Register register2 = userRepository.save(register);
		if (register2 != null) {
			Login login = new Login(register.getEmail(), register.getPassword(), register);
			if (loginRepository.existsByUserName(register.getEmail())) {
				throw new AlreadyExistsException("Record With " + register.getEmail() + " present in Login Table!");
			}
			String result = loginService.addCredentials(login);
			if (result.equals("success")) {
				return register;
			} else {
				return null;
			}
			// return "success";
		} else {
			return null;
		}
	}

	@Override
	public Optional<Register> getUserById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public List<Register> getAllUsersDetails() {
		return userRepository.findAll();
	}

	@Override
	public Optional<List<Register>> getAllUsersDetailsThroughOptional() {
		return Optional.ofNullable(userRepository.findAll());
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		Optional<Register> optional = userRepository.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No Record Found");
		} else {
			userRepository.deleteById(id);
			return "success";
		}
	}

	@Override
	public Register[] getAllUsers() {
		List<Register> list = userRepository.findAll();
		Register[] array = new Register[list.size()];
		return list.toArray(array);
	}

}
