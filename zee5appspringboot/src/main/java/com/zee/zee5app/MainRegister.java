package com.zee.zee5app;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Role;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.service.UserService;
import com.zee.zee5pp.utils.PasswordUtils;

@SpringBootApplication
public class MainRegister {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringbootApplication.class, args);

		UserService userService = applicationContext.getBean(UserService.class);
		RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
		PasswordUtils passwordUtils = applicationContext.getBean(PasswordUtils.class);

		System.out.print("Adding New User rg0075: ");
		Register register = new Register("rg0075", "Raghav", "Gupta", "ragahv.gupta75@zee.com", null, new BigInteger("7976196211"), null, null);
		register.setPassword(passwordUtils.generateSecurePassword("raghav123", passwordUtils.getSalt(30)));
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findById(1).get());
		roles.add(roleRepository.findById(2).get());
		register.setRoles(roles);
		Register result = null;
		try {
			result = userService.addUser(register);
		} catch (AlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(result);

//		Optional<Register> optional = userService.getUserById("rg0001");
//		if (optional.isEmpty()) {
//			System.out.println("No Such User Present");
//		} else {
//			System.out.println(optional.get());
//		}
//
//		userService.getAllUsersDetails().forEach(e -> System.out.println(e));
//
//		try {
//			System.out.println(userService.deleteUserById("rg0002"));
//		} catch (IdNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		Optional<List<Register>> listOptional = userService.getAllUsersDetailsThroughOptional();
//		listOptional.get().forEach(e -> System.out.println(e));
//
//		Register[] registers = userService.getAllUsers();
//		for (Register register2 : registers) {
//			System.out.println(register2);
//		}
//
//		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//		System.out.println(userRepository.existsByEmail("raghav.gupta@zee.com"));
//		System.out.println(userRepository.existsByEmail("raghavjpr@gmail.com"));

		applicationContext.close();
	}

}
