package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.enums.AUTORENEWAL;
import com.zee.zee5app.dto.enums.PAYMENTMODE;
import com.zee.zee5app.dto.enums.STATUS;
import com.zee.zee5app.dto.enums.TYPE;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5pp.utils.DBUtils;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {

	private DBUtils dbutils = null;

	private SubscriptionRepositoryImpl() throws IOException {
		dbutils = DBUtils.getInstance();
	}

	private static SubscriptionRepository subscriptionRepository;

	public static SubscriptionRepository getInstace() throws IOException {
		if (subscriptionRepository == null)
			subscriptionRepository = new SubscriptionRepositoryImpl();
		return subscriptionRepository;
	}

	@Override
	public String addSubscription(Subscription subscription) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "insert into subscription "
				+ "(id, dop, expiry, amount, paymentmode,status, type, autorenewal) "
				+ "values (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setString(1, subscription.getId());
			ps.setDate(2, new Date(subscription.getDateOfPurchase().getTime()));
			ps.setDate(3, new Date(subscription.getExpiryDate().getTime()));
			ps.setFloat(4, subscription.getAmount());
			ps.setString(5, subscription.getPaymentmode().toString());
			ps.setString(6, subscription.getStatus().toString());
			ps.setString(7, subscription.getType().toString());
			ps.setString(8, subscription.getAutorenewal().toString());
			//ps.setString(9, subscription.getRegId());

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
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public String updateSubscriptionById(String id, Subscription subscription) {
		Connection connection = dbutils.getConnection();
		String updateQuery = "UPDATE subscription SET "
				+ "dop = ?, expiry = ?, amount = ?, paymentmode = ?,status = ?, type = ?, autorenewal = ?"
				+ "where id = ?";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(updateQuery);

			ps.setDate(1, new Date(subscription.getDateOfPurchase().getTime()));
			ps.setDate(2, new Date(subscription.getExpiryDate().getTime()));
			ps.setFloat(3, subscription.getAmount());
			ps.setString(4, subscription.getPaymentmode().toString());
			ps.setString(5, subscription.getStatus().toString());
			ps.setString(6, subscription.getType().toString());
			ps.setString(7, subscription.getAutorenewal().toString());
			//ps.setString(8, subscription.getRegId());
			ps.setString(8, id);

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
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public String deleteSubscriptionById(String id) {
		Connection connection = dbutils.getConnection();
		String delQuery = "delete from subscription where id=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);
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
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String id)
			throws InvalidIdLengthException, InvalidAmountException {

		Connection connection = dbutils.getConnection();
		String getQuery = "select * from subscription where id=?";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			prepStatement.setString(1, id);
			ResultSet result = prepStatement.executeQuery();

			if (result.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(result.getString("id"));
				subscription.setDateOfPurchase(result.getDate("dop"));
				subscription.setExpiryDate(result.getDate("expiry"));
				subscription.setAmount(result.getFloat("amount"));
				subscription.setPaymentmode(PAYMENTMODE.valueOf(result.getString("paymentmode")));
				subscription.setStatus(STATUS.valueOf(result.getString("status")));
				subscription.setType(TYPE.valueOf(result.getString("type")));
				subscription.setAutorenewal(AUTORENEWAL.valueOf(result.getString("autorenewal")));
				//subscription.setRegId(result.getString("regId"));
				return Optional.of(subscription);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public List<Subscription> getAllSubscriptionsList() throws InvalidIdLengthException, InvalidAmountException {
		List<Subscription> subscriptions = new ArrayList<>();
		Connection connection = dbutils.getConnection();

		String getQuery = "select * from subscription";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(result.getString("id"));
				subscription.setDateOfPurchase(result.getDate("dop"));
				subscription.setExpiryDate(result.getDate("expiry"));
				subscription.setAmount(result.getFloat("amount"));
				subscription.setPaymentmode(PAYMENTMODE.valueOf(result.getString("paymentmode")));
				subscription.setStatus(STATUS.valueOf(result.getString("status")));
				subscription.setType(TYPE.valueOf(result.getString("type")));
				subscription.setAutorenewal(AUTORENEWAL.valueOf(result.getString("autorenewal")));
				//subscription.setRegId(result.getString("regId"));
				subscriptions.add(subscription);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}

		return subscriptions;
	}

	@Override
	public Subscription[] getAllSubscriptions() throws InvalidIdLengthException, InvalidAmountException {
		List<Subscription> subscriptions = this.getAllSubscriptionsList();
		return subscriptions.toArray(new Subscription[subscriptions.size()]);
	}

}
