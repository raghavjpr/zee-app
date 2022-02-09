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

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5pp.utils.DBUtils;

public class SeriesRepositoryImpl implements SeriesRepository {

	private DBUtils dbutils = null;

	private SeriesRepositoryImpl() throws IOException {
		dbutils = DBUtils.getInstance();
	}

	private static SeriesRepository seriesRepository;

	public static SeriesRepository getInstance() throws IOException {
		if (seriesRepository == null)
			seriesRepository = new SeriesRepositoryImpl();
		return seriesRepository;
	}

	@Override
	public String addSeries(Series series) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "INSERT INTO series "
				+ "(id, name, agelimit, genere, length, releaseDate, cast, language, noofepisodes) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(insertQuery);
			ps.setString(1, series.getId());
			ps.setString(2, series.getName());
			ps.setInt(3, series.getAgelimit());
			ps.setString(4, series.getGenere());
			ps.setInt(5, series.getLength());
			ps.setDate(6, new Date(series.getReleasedate().getTime()));
			ps.setString(7, String.join(",", series.getCast()));
			ps.setString(8, series.getLanguage());
			ps.setInt(9, 0);

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
	public String updateSeriesById(String id, Series series) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "UPDATE series SET name = ?, agelimit = ?, genere = ?, length = ?, "
				+ "releaseDate = ?, cast = ?, language = ?, noofepisodes = ? where id = ?";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(insertQuery);
			ps.setString(1, series.getName());
			ps.setInt(2, series.getAgelimit());
			ps.setString(3, series.getGenere());
			ps.setInt(4, series.getLength());
			ps.setDate(5, new Date(series.getReleasedate().getTime()));
			ps.setString(6, String.join(",", series.getCast()));
			ps.setString(7, series.getLanguage());
			ps.setInt(8, series.getNoofepisodes());
			ps.setString(9, series.getId());

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
	public String deleteSeriesById(String id) {
		Connection connection = dbutils.getConnection();
		String delQuery = "DELETE FROM series where id=?";
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
	public Optional<Series> getSeriesById(String id) throws InvalidIdLengthException {
		Connection connection = dbutils.getConnection();

		String getQuery = "SELECT * FROM series where id=?";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			prepStatement.setString(1, id);
			ResultSet result = prepStatement.executeQuery();

			if (result.next()) {
				Series series = new Series();
				series.setId(result.getString("id"));
				series.setName(result.getString("name"));
				series.setAgelimit(result.getInt("agelimit"));
				series.setReleasedate(result.getDate("releasedate"));
				series.setGenere(result.getString("genere"));
				series.setLength(result.getInt("length"));
				series.setCast(result.getString("cast").split(","));
				series.setLanguage(result.getString("language"));
				series.setNoofepisodes(result.getInt("noofepisodes"));
				return Optional.of(series);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}

		return Optional.empty();
	}

	@Override
	public List<Series> getAllSeriesList() throws InvalidIdLengthException {
		List<Series> seriess = new ArrayList<>();
		Connection connection = dbutils.getConnection();

		String getQuery = "SELECT * FROM series";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				Series series = new Series();
				series.setId(result.getString("id"));
				series.setName(result.getString("name"));
				series.setAgelimit(result.getInt("agelimit"));
				series.setReleasedate(result.getDate("releasedate"));
				series.setGenere(result.getString("genere"));
				series.setLength(result.getInt("length"));
				series.setCast(result.getString("cast").split(","));
				series.setLanguage(result.getString("language"));
				series.setNoofepisodes(result.getInt("noofepisodes"));
				seriess.add(series);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return seriess;
	}

	@Override
	public Series[] getAllSeries() throws InvalidIdLengthException {
		List<Series> series = this.getAllSeriesList();
		return series.toArray(new Series[series.size()]);
	}
}
