package com.zee.zee5app.dto;

import java.util.Objects;

import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
//@EqualsAndHashCode
public class Register implements Comparable<Register> {
	
	
	public Register(String id, String firstName, String lastName, String email, String password, String contactNumber) throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
	}

	@Setter(value = AccessLevel.NONE)
	private String id;
	// It should have a minimum length of 6.
	// We have to write the code to validate the length and then assign the value.
	@Setter(value = AccessLevel.NONE)
	private String firstName;
	@Setter(value = AccessLevel.NONE)
	private String lastName;
	@Setter(value = AccessLevel.NONE)
	private String email;
	private String password;
	private String contactNumber;

//	public Register() {
//		// EDC
//		// any kind of customization during the initialization of object
//		// then its better to introduce EDC or PC or both as per the need.
//
//		System.out.println("hello ");
//	}

	public void setId(String id) throws InvalidIdLengthException {
		// throws : it will provide the list of exception may be raised
		// it will hold the list of checked exceptions.
		
		if(id.length() < 6) { 
			// It should raise the InvalidIdLengthException
			// In normal case or in case of predefined exception object is created by JVM.
			// User defined exception object must be raised by us
			throw new  InvalidIdLengthException("Id length is less than or equal to 6");
			// throw : it will throw the exception			
		}
		this.id = id;
	}
	
	public void setFirstName(String firstName) throws InvalidNameException {
		
		if(firstName == null || firstName == "" || firstName.length() < 2) {
			throw new InvalidNameException("First name not valid.");
		}
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) throws InvalidNameException {
		
		if(lastName == null || lastName == "" || lastName.length() < 2) {
			throw new InvalidNameException("Last name not valid.");
		}
		this.lastName = lastName;
	}
	
	public void setEmail(String email) throws InvalidEmailException {
		if(!email.contains("@")) {
			throw new InvalidEmailException("Email Id not valid.");
		}
			
		this.email = email;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		//return this.id.compareTo(o.getId()); // ascending
		return o.id.compareTo(this.getId());   // descending
	}
	// for compareTo to work we need to implement Comparable.
	
	
	
	

	// private stuff will be accessible only inside the class.

	// setter : is used to set the value for a particular field.
	// getter : to get/return the value of a specific field

}