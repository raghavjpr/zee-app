package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exception.IdNotFoundException;

public interface UserService {

	public User addUser(User user) throws IdNotFoundException;

	public List<User> getAllUsersDetails();

	public Optional<List<User>> getAllUsersDetailsThroughOptional();

	public String deleteUserById(Long id) throws IdNotFoundException;

	public User[] getAllUsers();

	public Optional<User> getUserById(Long id);
}
