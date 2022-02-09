package com.zee.zee5app.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SeriesRepository;

@Repository
public class SeriesRepositoryImpl implements SeriesRepository {

	public SeriesRepositoryImpl() {
	}

	@Autowired
	DataSource dataSource;

	@Override
	public String addSeries(Series series) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		}
		return "fail";
	}

	@Override
	public String updateSeriesById(String id, Series series) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		}
		return "fail";
	}

	@Override
	public String deleteSeriesById(String id) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String delQuery = "DELETE FROM series where id=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);
			int result = prepStatement.executeUpdate();
			if (result > 0) {
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
		}
		return "fail";
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws InvalidIdLengthException {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		}

		return Optional.empty();
	}

	@Override
	public List<Series> getAllSeriesList() throws InvalidIdLengthException {
		List<Series> seriess = new ArrayList<>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		}
		return seriess;
	}

	@Override
	public Series[] getAllSeries() throws InvalidIdLengthException {
		List<Series> series = this.getAllSeriesList();
		return series.toArray(new Series[series.size()]);
	}
}
