package com.zee.zee5app.service;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.repository.UserRepository2;
// service objects are used to just call the services inside the main method
//can we do this using 1 object?
// TO fix they provided a solution called singleton Design pattern
// one ==> to get only one object for the specific type.


public class UserService2 {

	// service is consuming the repository
	
	private UserService2() {
		// TODO Auto-generated constructor stub
	}
	// if we want to create that single object then we have to create it
	// inside the same class
	// and we have to share reference with others
	// to do the same we have to declare a method.
	private static UserService2  service = null;
	// this would be a static 
	// only one copy.
	
	public static UserService2 getInstance() {
		// it  becomes object independent?
		// static will make it independent on the object for execution.
		
		// static method will access only static reference
		if(service==null)
		service = new UserService2();
		return service;
		
	}
	
	private UserRepository2 repository = UserRepository2.getInstance();
	
	public String addUser(Register register) {
		// do we need to consume the addUser method from repo.
		return this.repository.addUser(register);
	}
	
	public Register getUserById(String id) {
		return repository.getUserById(id);
	}
	public Register[] getUsers() {
		return repository.getUsers();
	}
}

// series TreeSet
// movies HashSet
// subscription ArrayList 
// InvalideAmountException
// IDNOTFOUND
// NameNotFound
// LocationNotFound