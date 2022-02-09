package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;

public interface UserService {

	public Register addUser(Register register) throws AlreadyExistsException;

	public Optional<Register> getUserById(String id);

	public List<Register> getAllUsersDetails();

	public Optional<List<Register>> getAllUsersDetailsThroughOptional();

	public String deleteUserById(String id) throws IdNotFoundException;

	public Register[] getAllUsers();
}
