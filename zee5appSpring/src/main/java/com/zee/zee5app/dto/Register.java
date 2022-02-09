package com.zee.zee5app.dto;

import java.math.BigDecimal;

import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter

public class Register {

	@Setter(value = AccessLevel.NONE)
	private String id;
	@Setter(value = AccessLevel.NONE)
	private String firstName;
	@Setter(value = AccessLevel.NONE)
	private String lastName;
	@Setter(value = AccessLevel.NONE)
	private String email;
	private String password;
	private BigDecimal contactNumber;

	public Register(String id, String firstName, String lastName, String email, String password,
			BigDecimal contactNumber) throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.password = password;
		this.contactNumber = contactNumber;
	}

	public void setId(String id) throws InvalidIdLengthException {
		if (id.length() < 6) {
			throw new InvalidIdLengthException("Id length is less than or equal to 6");
		}
		this.id = id;
	}

	public void setFirstName(String firstName) throws InvalidNameException {

		if (firstName == null || firstName == "" || firstName.length() < 2) {
			throw new InvalidNameException("First name not valid.");
		}
		this.firstName = firstName;
	}

	public void setLastName(String lastName) throws InvalidNameException {

		if (lastName == null || lastName == "" || lastName.length() < 2) {
			throw new InvalidNameException("Last name not valid.");
		}
		this.lastName = lastName;
	}

	public void setEmail(String email) throws InvalidEmailException {
		if (!email.contains("@")) {
			throw new InvalidEmailException("Email Id not valid.");
		}

		this.email = email;
	}
}