package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.enums.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5pp.utils.DBUtils;

public class LoginRepositoryImpl implements LoginRepository {

	DBUtils dbutils = null;

	private LoginRepositoryImpl() throws IOException {
		// TODO Auto-generated constructor stub
		dbutils = DBUtils.getInstance();
	}

	private static LoginRepository loginRepository = null;

	public static LoginRepository getInstance() throws IOException {
		if (loginRepository == null)
			loginRepository = new LoginRepositoryImpl();
		return loginRepository;
	}

	@Override
	public String addCredentials(Login login) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "insert into login (username, password, register_id, role)" + "values(?,?,?,?)";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(insertQuery);
			prepStatement.setString(1, login.getUserName());
			prepStatement.setString(2, login.getPassword());
			prepStatement.setString(3, login.getRegID());
			prepStatement.setString(4, login.getRole().toString());
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

	@Override
	public String deleteCredentials(String userName) {
		Connection connection = dbutils.getConnection();
		String delQuery = "delete from login where username=?";
		try {
			PreparedStatement ps = connection.prepareStatement(delQuery);
			ps.setString(1, userName);
			int result = ps.executeUpdate();
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

	@Override
	public String changePassword(String userName, String password) {
		Connection connection = dbutils.getConnection();
		String updateQuery = "update login set password=? where username=?";
		try {
			PreparedStatement ps = connection.prepareStatement(updateQuery);
			ps.setString(1, password);
			ps.setString(2, userName);
			int result = ps.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			}
			return "fail";

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
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		String updateStatement = "update login set role=? where username=?";
		Connection connection = dbutils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, role.toString());
			preparedStatement.setString(2, userName);

			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			} else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			dbutils.closeConnection(connection);
		}
		return "fail";
	}
}
