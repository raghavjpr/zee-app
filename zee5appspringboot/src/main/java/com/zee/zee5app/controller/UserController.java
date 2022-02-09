package com.zee.zee5app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody Register register) throws AlreadyExistsException {
		// It should store the received info into DB.
//		try {
//			Register register2 = userService.addUser(register);
//			System.out.println(register2);
//			return ResponseEntity.status(201).body(register2);
//		} catch (AlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			Map<String, String> hasMap = new HashMap<String, String>();
//			hasMap.put("message", "Record already exists");
//			return ResponseEntity.badRequest().body(hasMap);
//		}
		// Validation
		// return the crisp info to the client
		// Customization in error response
		// Declaration of custom exception
		Register register2 = userService.addUser(register);
		System.out.println(register2);
		return ResponseEntity.status(201).body(register2);
	}

	// insert 20 records
	// retrieve a specific record

	@GetMapping("/{registerId}")
	public ResponseEntity<?> getUserById(@PathVariable("registerId") String registerId) throws IdNotFoundException {
		Optional<Register> optional = userService.getUserById(registerId);
		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "No Such Record.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}

		return ResponseEntity.ok(optional.get());
	}
	// retrieving all records

	@GetMapping("/all")
	public ResponseEntity<?> getAllUserDetails() {
		Optional<List<Register>> optional = userService.getAllUsersDetailsThroughOptional();
		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "No Records Present.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}

		return ResponseEntity.ok(optional.get());
	}

	// page wise records
}
