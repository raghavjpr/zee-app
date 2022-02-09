package com.zee.zee5app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5pp.utils.PasswordUtils;

@Repository
// It will create the singleton object for us.

public class UserRepositoryImpl implements UserRepository {

	// It will bring already created objects wither by using name/type
	@Autowired
	DataSource dataSource;
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	PasswordUtils passwordUtils;

	public UserRepositoryImpl() {
	}

	@Override
	public String updateUser(String id, Register register) {
		return null;
	}

	@Override
	public Optional<Register> getUserById(String id)
			throws InvalidNameException, InvalidEmailException, InvalidIdLengthException {

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String selectStatement = "select * from register where register_id=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Register register = new Register();
				register.setId(resultSet.getString("register_id"));
				register.setFirstName(resultSet.getString("firstname"));
				register.setLastName(resultSet.getString("lastname"));
				register.setEmail(resultSet.getString("email"));
				register.setPassword(resultSet.getString("password"));
				register.setContactNumber(resultSet.getBigDecimal("contactnumber"));
				return Optional.of(register);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<Register> getAllUsersDetails()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
		}
		return null;
	}

	@Override
	public String deleteUserById(String id) {

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		String delQuery = "delete from register where register_id=?";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);

			int result = prepStatement.executeUpdate();
			if (result > 0) {
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
		}
		return "fail";
	}

	@Override
	public Optional<List<Register>> getAllUsersDetailsThroughOptional()
			throws InvalidIdLengthException, InvalidNameException, InvalidEmailException {

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
	public String addUser(Register register) {

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		String insertQuery = "insert into register"
				+ "(register_id, firstname, lastname, email, contactnumber, password)" + "values(?,?,?,?,?,?)";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(insertQuery);
			prepStatement.setString(1, register.getId());
			prepStatement.setString(2, register.getFirstName());
			prepStatement.setString(3, register.getLastName());
			prepStatement.setString(4, register.getEmail());
			prepStatement.setBigDecimal(5, register.getContactNumber());
			String encryptPassword = passwordUtils.generateSecurePassword(register.getPassword(),
					passwordUtils.getSalt(30));
			prepStatement.setString(6, encryptPassword);

			int result = prepStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
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
		}
		return "fail";
	}
}