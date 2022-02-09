package com.zee.zee5app;

import java.util.Optional;

//import java.util.Iterator;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.impl.UserServiceImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Register register = new Register();
		//Register : class name
		// register : reference which will refer your object
		// new : is used to create the object 
		// Register() : constructor ===> DC ==> IDC
		
		try {
			register.setFirstName("Raghav");
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
		try {
			register.setId("rg0000");
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(register);
		// whenever you will print the reference then it 
		// will call toString() method.
		
		System.out.println(register.toString());
	
		System.out.println(register.getEmail());
		
		// can u create the login object?
		// then can u print the login details?
		
		UserService service = UserServiceImpl.getInstance();
		
		for(int i=1;i<=20;i++) {
		
			Register register2 = new Register();
			try {
				register2.setId("rg000" + i);
				register2.setFirstName("Raghav" + i);
				register2.setLastName("Gupta" + i);
				register2.setEmail("raghav.gupta" + i + "@zee.com");
			} catch (InvalidIdLengthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidEmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			register2.setPassword("raghav" + i);
			register2.setContactNumber("7976" + i);
			String result = service.addUser(register2);
			System.out.println(result);
		}
		
//		for(int i=1;i<=20;i++) {
//			
//			Register register2 = new Register();
//			try {
//				register2.setId("rg000" + i);
//				register2.setFirstName("Raghav" + i);
//				register2.setLastName("Gupta" + i);
//				register2.setEmail("raghav.gupta" + i + "@zee.com");
//			} catch (InvalidIdLengthException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvalidNameException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvalidEmailException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			register2.setPassword("raghav" + i);
//			String result = service.addUser(register2);
//			System.out.println(result);
//		}
		//// For checking if the linked hash set is working properly or not.
		
//		for(int i=1;i<=20;i++) {
//			
//			Register register2 = new Register();
//			try {
//				register2.setId("rg0000");
//				register2.setFirstName("Raghav");
//				register2.setLastName("Gupta");
//				register2.setEmail("raghav.gupta@zee.com");
//			} catch (InvalidIdLengthException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvalidNameException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvalidEmailException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}catch (Exception e) {
//				// TODO: handle exception
//			}catch (Throwable e) {
//				// TODO: handle exception
//			}
//			
//			register2.setPassword("raghav");
//			String result = service.addUser(register2);
//			System.out.println(result);
//		}
//		// as you can see the hash set is allowing duplicate values because we have not overridden the hash code and equals.
		
	
		// UserService object
		// main is consuming the service ?
		
//		Register register2 = service.getUserById("rg000001");
//		System.out.println(register2!=null);
//		
//		for (Register register3 : service.getAllUsers()) {
//			if(register3!=null)
//			System.out.println(register3);
//		}
		
		service.getAllUsersDetails().forEach(e->System.out.println(e));
		
				
//		try {
//			Optional<Register> optional = service.getUserById("rg0001");
//		} catch (IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
////		if (optional.isPresent()) {
////			System.out.println("getUserById " +  optional.get());
////		} else {
////			System.out.println("Id not found.");
////		}
		
		try {
			System.out.println(service.deleteUserById("rg000000001"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(service.deleteUserById("rg0001"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		service.getAllUsersDetails().forEach(e->System.out.println(e));
		
//		try {
//			System.out.println(service.updateUser("rg00016", register));
//		} catch (IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		//service.getAllUsersDetails().forEach(e->System.out.println(e));
		
		
		
//		try {
//			Register register3 = new Register("test001", "Tester1", "Man1", "tester.man1@test.com", "test1231");
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Register register4 = new Register("test002", "Tester2", "Man2", "tester.man2@test.com", "test1232");
//		} catch (InvalidIdLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidEmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//UserRepository repository = new UserRepository(); Error
		//can't create the instance.
		
		//UserRepository repository = null;
		//creation of instance is valid.
	}

}