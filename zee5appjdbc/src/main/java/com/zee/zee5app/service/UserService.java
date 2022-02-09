package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

public interface UserService {
	public String addUser(Register register);

	public String updateUser(String id, Register register);

	public Optional<Register> getUserById(String id);

	public List<Register> getAllUsersDetails()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException;

	public Optional<List<Register>> getAllUsersDetailsThroughOptional()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException;

	public String deleteUserById(String id);

	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException;
}