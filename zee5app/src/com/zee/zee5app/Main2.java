package com.zee.zee5app;

import java.util.Iterator;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.UserService2;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Register register = new Register();
		//Register : class name
		// register : reference which will refer your object
		// new : is used to create the object 
		// Register() : constructor ===> DC ==> IDC
		
		try {
			register.setFirstName("Raghav");
		} catch (InvalidNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			register.setLastName("Gupta");
		} catch (InvalidNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			register.setEmail("raghav.gupta@zee.com");
		} catch (InvalidEmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		register.setPassword("raghav123");
		register.setContactNumber("7976");
		
		System.out.println(register);
		// whenever you will print the reference then it 
		// will call toString() method.
		
		System.out.println(register.toString());
	
		System.out.println(register.getEmail());
		
		// can u create the login object?
		// then can u print the login details?
		
		UserService2 service = UserService2.getInstance();
		
		for(int i=1;i<=20;i++) {
		
			Register register2 = new Register();
			try {
				register2.setId("rg000000" + i);
			} catch (InvalidIdLengthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				register2.setFirstName("Raghav" + i);
				register2.setLastName("Gupta" + i);
			} catch (InvalidNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			register2.setPassword("raghav" + i);
			register2.setContactNumber("7976" + i);
			String result = service.addUser(register2);
			System.out.println(result);
		}
		
			
		// UserService object
		// main is consuming the service ?
		
		Register register2 = service.getUserById("rg001");
		System.out.println(register2!=null);
		
		for (Register register3 : service.getUsers()) {
			if(register3!=null)
			System.out.println(register3);
		}
		
		//UserRepository repository = new UserRepository(); Error
		//can't create the instance.
		
		UserRepository repository = null;
		//creation of instance is valid.
	}

}