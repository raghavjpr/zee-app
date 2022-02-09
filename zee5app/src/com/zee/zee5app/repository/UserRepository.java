package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;

public interface UserRepository {
	
	public String addUser(Register register);
	public String updateUser(String id, Register register) throws IdNotFoundException;
	public Optional<Register> getUserById(String id) throws IdNotFoundException;
	public Register[] getAllUsers();
	public List<Register> getAllUsersDetails();
	public String deleteUserById(String id) throws IdNotFoundException;

}

// we can't create/declare the objects.
// we can declare only references.
// when we will refer the objects whose class is implementing the interface.
// then that object will behave like an interface i.e.
// we can get only access for the interface overridden methods.