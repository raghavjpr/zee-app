package com.zee.zee5app.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
	
	// we need singleton object for Repository.
	private UserRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private static UserRepository repository;
	public static UserRepository getInstance() {
		if(repository == null)
			repository = new UserRepositoryImpl();
		return repository;
	}
	
//	private Register[] registers = new Register[10];
	
	//private ArrayList<Register> arrayList = new ArrayList<Register>();
	// when we use default constructor of array list it will hold 10 elements of type <>. Here Register.
	
	//private List<Register> arrayList = new ArrayList<Register>();
	
	//private List<Register> arrayList = new LinkedList<>();
	// When searching Array List. Random Insertion and deletion Linked List
	
	//private Set<Register> setR = new HashSet();
	// when we use default constructor of hash set it will hold 16 elements of type <>. Here Register.
	
	//private Set<Register> setR = new LinkedHashSet();
	// Linked Hash set is use full if we want the elements in the insertion order but with no duplicates.
	// also 16
	// if we know we have more than 16 elements go for another constructor.
	// loadFactor ==> 75%. When the hash set has 75 percent occupancy than it go for expansion.
	// tried and tested 75% point.
	
	private TreeSet<Register> setR = new TreeSet();
	// sorted order
	// BST Left Root Right ==> It needs Comparable ==> It has compareTo ==> can compare objects.
	// Comparator ==> when the comparing criteria is changing.
	
	
	//private static int count = -1;
//
//	@Override
//	public String addUser(Register register) {
//		
//		//registers.length ====> it will give us the availability
//		
//		if(count==registers.length-1) {
//			// array is full or we should go for dynamically growing the 
//			// size of array.
//			Register temp[] = new Register[registers.length*2];
//			
//			// do we need to copy the contents from old to new? Yes
//			
//			System.arraycopy(registers, 0, temp, 0, registers.length);
//			registers= temp;
//			registers[++count] = register;
//			
//			
//			return "success";
//			
//		}
//		registers[++count] = register;
//		return "success";	
//	}
//
//	@Override
//	public String updateUser(String id, Register register) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Register getUserById(String id) {
//		
//		for (Register register : registers) {
//			if(register!=null && register.getId().equals(id) ) {
//				return register;
//			}
//		}
//		
//		return null;
//	}
//
//	@Override
//	public Register[] getAllUsers() {
//		// TODO Auto-generated method stub
//		return registers;
//	}
//
//	@Override
//	public String deleteUserById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		boolean result = this.setR.add(register);
		if(result) {
			return "Success";
		}
		return "Fail";
	}

//	@Override
//	public String updateUser(String id, Register register) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Register> optional = this.getUserById(id);
//		if (optional.isPresent()) {
//			int itemIndex = arrayList.indexOf(optional.get());
//			Register result = arrayList.set(itemIndex, register);
//			return "success";
//		}
//		return "fail";
//	}
	
	@Override
	public String updateUser(String id, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Register getUserById(String id) {
//		// TODO Auto-generated method stub
//		// we need to traverser the collection or the Array List
//		for (Register register : arrayList) {
//			if(register != null && register.getId().equals(id)) {
//				return register;
//			}
//		}
//		return null;
//	}
	
	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException  {
		// TODO Auto-generated method stub
		// This is a class specifically designed to deal the Null Pointer Exception.
		Register register2 = null;
		for (Register register : setR) {
			if(register.getId().equals(id)) {
				//return Optional.of(register);
				register2 = register;
				break;
			}
		}
		//return Optional.empty();
		//return Optional.ofNullable(register2);
		// If register2 is holding null it will act like .empty().
		// If register2 is holding object it will act like .of()
		//return Optional.ofNullable(Optional.of(register2).orElseThrow(()-> new IdNotFound("Id Not Found")));
		return Optional.of(Optional.ofNullable(register2).orElseThrow(()-> new IdNotFoundException("Id not found.")));
		
	}
	
//	@Override
//	public Register getUserById(String id) throws IdNotFound {
//		// TODO Auto-generated method stub
//		Register register2 = null;
//		for (Register register : arrayList) {
//			if(register.getId().equals(id)) {
//				register2 = register;
//			}
//		}
//		if (register2 == null) {
//			throw new IdNotFound("Id not found.");
//		} else {
//			return register2;
//		}
//	}

	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		// Transform the collection to an Array.
		
		Register register[] = new Register[setR.size()];
		return setR.toArray(register);
	}

//	@Override
//	public String deleteUserById(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		// id is the input but ArrayList have registers.
//		// we can just use the method to get user by id.
//		
//		Optional<Register> optional = this.getUserById(id);
//		if (optional.isPresent()) {
//			// removal
//			boolean result = arrayList.remove(optional.get());
//			if (result) {
//				return "success";
//			} else {
//				return "fail";
//			}
//		} else {
//			//throw IdNotFoundException
//			throw new IdNotFoundException("Id to be deleted not found.");
//		}
//	}
	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Register> optional = this.getUserById(id);
		if (optional.isPresent()) {
			// removal
			setR.remove(optional.get());
			return "success";
		}
		return "fail";
	}

//	@Override
//	public List<Register> getAllUsersDetails() {
//		// TODO Auto-generated method stub
//		// Here we are returning all the details but now we need to return it in terms of sorted order.
////		Collections.sort(arrayList);
////		return arrayList;
//		ArrayList<Register> arraylist = new ArrayList<>(setR);
//		Collections.sort(arraylist);
//		return arraylist;
//	}//Hash set and sort
	
	@Override
	public List<Register> getAllUsersDetails() {
		// TODO Auto-generated method stub
		// Back end it is in descending but i want ascending.
		//return new ArrayList<>(setR);
		return new ArrayList<>(setR.descendingSet());
	}
	
	
	
}
