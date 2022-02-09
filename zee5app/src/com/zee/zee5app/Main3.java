package com.zee.zee5app;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

public class Main3 {

	public static void main(String[] args) {
		
		try {
			Register register = new Register("rg0001", "Raghav", "Gupta", "raghav.gupta@zee.com", "raghav123","9999");
			
			System.out.println(register);
			System.out.println(register.toString());
			System.out.println(register.hashCode());
			Register register2 = new Register("rg0001", "Raghav", "Gupta", "raghav.gupta@zee.com", "raghav123","9999");
			System.out.println(register2);
			System.out.println(register2.toString());
			System.out.println("hash code " + register2.hashCode());
			System.out.println(register);
			System.out.println(register.toString());
			System.out.println("hash code " + register.hashCode());
			System.out.println("Equals method call" + register.equals(register2));
		} catch (InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}