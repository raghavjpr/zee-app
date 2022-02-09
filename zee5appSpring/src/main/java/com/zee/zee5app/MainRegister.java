package com.zee.zee5app;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.service.UserService;

public class MainRegister {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		UserService userService = applicationContext.getBean(UserService.class);

		System.out.print("Adding New User rg03020401: ");
		try {
			Register register = new Register("rg03020401", "Raghav", "Gupta", "ragahv.gupta31@zee.com", "raghav123",
					new BigDecimal("7976196211"));
			String result = userService.addUser(register);
			System.out.println(result);
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.print("Searching for rg00003: ");

		Optional<Register> result = null;
		try {
			result = userService.getUserById("rg00003");
		} catch (InvalidNameException | InvalidEmailException | InvalidIdLengthException e1) {
			e1.printStackTrace();
		}
		System.out.println(result.get());
//
//		System.out.println();
//		System.out.print("Printing all Users: ");
//
//		try {
//			List<Register> resultList = userService.getAllUsersDetails();
//			resultList.forEach(e -> System.out.println(e));
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
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

//		System.out.println();
//		System.out.print("Deleting User rg000003: ");
//
//		try {
//			String result = userService.deleteUserById("rg000003");
//			System.out.println(result);
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println();
//		System.out.print("Printing all Users: ");
//		System.out.println();
//
//		try {
//			List<Register> resultList = userService.getAllUsersDetails();
//			resultList.forEach(e -> System.out.println(e));
//		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		applicationContext.close();
	}
}