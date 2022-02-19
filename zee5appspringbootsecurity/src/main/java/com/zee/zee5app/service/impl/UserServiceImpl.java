package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User addUser(User user) throws IdNotFoundException {
		if (!userRepository.existsByUsername(user.getUsername())) {
			throw new IdNotFoundException("Sorry user with " + user.getUsername() + " not found");
		}
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getAllUsersDetails() {
		return userRepository.findAll();
	}

	@Override
	public Optional<List<User>> getAllUsersDetailsThroughOptional() {
		return Optional.ofNullable(userRepository.findAll());
	}

	@Override
	public String deleteUserById(Long id) throws IdNotFoundException {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No Record Found");
		} else {
			userRepository.deleteById(id);
			return "success";
		}
	}

	@Override
	public User[] getAllUsers() {
		List<User> list = userRepository.findAll();
		User[] array = new User[list.size()];
		return list.toArray(array);
	}

}
