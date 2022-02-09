package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5pp.utils.DBUtils;
import com.zee.zee5pp.utils.PasswordUtils;

public class UserRepositoryImpl implements UserRepository {

	private static DBUtils dbutils = null;
	private static LoginRepository loginRepository = null;

	// we need singleton object for Repository.
	private UserRepositoryImpl() throws IOException {
		dbutils = DBUtils.getInstance();
		loginRepository = LoginRepositoryImpl.getInstance();
	}

	private static UserRepository repository = null;

	public static UserRepository getInstance() throws IOException {
		if (repository == null)
			repository = new UserRepositoryImpl();
		return repository;
	}

//	@Override
//	public String addUser(Register register) throws InvalidIdLengthException {
//		// Connection object
//		Connection connection = dbUtils.getConnectin();
//
//		// Add the user details to the table.
//		String insertStatement = "INSERT INTO register"
//				+ " (register_id, firstname, lastname, email, contactnumber, password)" + " VALUES(? ,? ,? ,? ,? ,?);";
//		// We will concatenate the values in values specification
//		// We will use ?
//		// Here we will provide the values against ? (place holder)
//
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
//			// We need to provide the values against ? placeholder
//			preparedStatement.setString(1, register.getId());
//			preparedStatement.setString(2, register.getFirstName());
//			preparedStatement.setString(3, register.getLastName());
//			preparedStatement.setString(4, register.getEmail());
//			preparedStatement.setBigDecimal(5, register.getContactNumber());
//			String salt = PasswordUtils.getSalt(30);
//			String encryptedPassword = PasswordUtils.generateSecurePassword(register.getPassword(), salt);
//			preparedStatement.setString(6, encryptedPassword);
//
//			// For login Table data is taken from here
//			// username : email
//			// password : password
//			// register_id : id
//			// we will get all these details during registration
//
//			int result = preparedStatement.executeUpdate();
//			// Above statement is returning integer ===> It returns the no or rows affected
//			// by the DML Statement.
//			// Insert Statement will return 1 ==> one row is affected or one row is inserted
//			// Delete Statement returning 1,2 or x no of row ==> x rows affected
//			// Same with update and any other statement.
//			if (result > 0) {
//				Login login = new Login(register.getEmail(),encryptedPassword,register.getId());
//				System.out.println(login);
//				String resultLogin = loginRepository.addCredentials(login);
//				if (resultLogin.equals("success")) {
//					return "success";
//				} else {
//					connection.rollback();
//					return "fail";
//				}
//			} else {
//				connection.rollback();
//				return "fail";
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {
//				connection.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			return "fail";
//		} finally {
//			// closure work
//			// closing the connection
//			dbUtils.closeConnection(connection);
//		}
//	}

	@Override
	public String addUser(Register register) {

		Connection connection = dbutils.getConnection();
		String insertQuery = "insert into register"
				+ "(register_id, firstname, lastname, email, contactnumber, password)" + "values(?,?,?,?,?,?)";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(insertQuery);
			prepStatement.setString(1, register.getId());
			prepStatement.setString(2, register.getFirstName());
			prepStatement.setString(3, register.getLastName());
			prepStatement.setString(4, register.getEmail());
			prepStatement.setBigDecimal(5, register.getContactNumber());
			String encryptPassword = PasswordUtils.generateSecurePassword(register.getPassword(),
					PasswordUtils.getSalt(30));
			prepStatement.setString(6, encryptPassword);

			int result = prepStatement.executeUpdate();
			if (result > 0) {
				Login login = new Login(register.getEmail(), encryptPassword, register.getId(), ROLE.ROLE_USER);
				String status = loginRepository.addCredentials(login);
				if (status.equals("success")) {
					return "success";
				} else {
					connection.rollback();
					return "fail";
				}
			} else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public Optional<Register> getUserById(String id) {

		Connection connection = dbutils.getConnection();
		String selectStatement = "select * from register where register_id=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			// To retrieve the data we use executeQuery
			// Result Set will hold the result of the query
			// This object is single which is holding all the records.
			// We have to traverse it to get the results
			// System.out.println(resultSet);
			if (resultSet.next()) {
				// next method is used to traverse the ResultSet.
				// Initially the result set points to the first row. When we call for the first
				// time it will retrieve and give us the first record and then it will point to
				// the second row.
				Register register = new Register();
				register.setId(resultSet.getString("register_id"));
				register.setFirstName(resultSet.getString("firstname"));
				register.setLastName(resultSet.getString("lastname"));
				register.setEmail(resultSet.getString("email"));
				register.setPassword(resultSet.getString("password"));
				register.setContactNumber(resultSet.getBigDecimal("contactnumber"));
				return Optional.of(register);
			}
		} catch (SQLException | InvalidIdLengthException | InvalidNameException | InvalidEmailException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public List<Register> getAllUsersDetails()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {

		Connection connection = dbutils.getConnection();
		String selectStatement = "select * from register";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Register> arrayList = new ArrayList<Register>();

			while (resultSet.next()) {

				Register register = new Register(resultSet.getString("register_id"), resultSet.getString("firstname"),
						resultSet.getString("lastname"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getBigDecimal("contactnumber"));

				arrayList.add(register);
			}
			return arrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return null;
	}

	@Override
	public String deleteUserById(String id) {

		Connection connection = dbutils.getConnection();
		String delQuery = "delete from register where register_id=?";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);

			int result = prepStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public Optional<List<Register>> getAllUsersDetailsThroughOptional()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {

		Connection connection = dbutils.getConnection();
		String selectStatement = "select * from register";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Register> arrayList = new ArrayList<Register>();

			while (resultSet.next()) {

				Register register = new Register(resultSet.getString("register_id"), resultSet.getString("firstname"),
						resultSet.getString("lastname"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getBigDecimal("contactnumber"));

				arrayList.add(register);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {

		Optional<List<Register>> optionalList = this.getAllUsersDetailsThroughOptional();

		if (optionalList.isEmpty()) {
			return null;
		} else {
			List<Register> listRegister = optionalList.get();
			Register[] registers = new Register[listRegister.size()];
			return listRegister.toArray(registers);
		}
	}

	@Override
	public String updateUser(String id, Register register) {
		Connection connection = dbutils.getConnection();
		String updateStatement = "update register set firstname=?,lastname=?, email=?, contactnumber=?, password=? where register_id=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(updateStatement);
			prepStatement.setString(6, register.getId());
			prepStatement.setString(1, register.getFirstName());
			prepStatement.setString(2, register.getLastName());
			prepStatement.setString(3, register.getEmail());
			prepStatement.setBigDecimal(4, register.getContactNumber());
			String encryptPassword = PasswordUtils.generateSecurePassword(register.getPassword(),
					PasswordUtils.getSalt(30));
			prepStatement.setString(5, encryptPassword);
			int result = prepStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";

			} else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}

		return "fail";
	}
}