package com.zee.zee5app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.impl.UserServiceImpl;

public class MainRegister {

	public static void main(String[] args) {

		UserService userService = null;
		try {
			userService = UserServiceImpl.getInstance();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.print("Adding New User rg00000002: ");
		try {
			Register register = new Register("rg00000002", "Raghav", "Gupta", "ragahv.gupta02@zee.com", "raghav123",
					new BigDecimal("7976196211"));
			String result = userService.addUser(register);
			System.out.println(result);
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.print("Searching for rg00003: ");

		Optional<Register> result = userService.getUserById("rg00003");
		System.out.println(result.get());

		System.out.println();
		System.out.print("Printing all Users: ");

		try {
			List<Register> resultList = userService.getAllUsersDetails();
			resultList.forEach(e -> System.out.println(e));
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println();
		System.out.print("Printing all Users: ");
		System.out.println();

		try {
			Optional<List<Register>> resultOptional = userService.getAllUsersDetailsThroughOptional();
			if (resultOptional.isEmpty()) {
				System.out.println("No records Present!");
			} else {
				resultOptional.get().forEach(e -> System.out.println(e));
			}
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.print("Deleting User rg00000025: ");

		String result1 = userService.deleteUserById("rg00000025");
		System.out.println(result1);

		System.out.println();
		System.out.print("Printing all Users: ");
		System.out.println();

		try {
			List<Register> resultList = userService.getAllUsersDetails();
			resultList.forEach(e -> System.out.println(e));
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println();
		System.out.print("Updating rg00000005: ");
		
		Register register;
		try {
			register = new Register("rg00000005", "RaghavToX", "GuptaToX", "raghavToX@zee.com", "raghav325",new BigDecimal("65417398"));
			String result11 = userService.updateUser("rg00000005", register);
			System.out.println(result11);
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}