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

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5pp.utils.DBUtils;

public class MovieRepositoryImpl implements MovieRepository {
	private static MovieRepository movieRepository = null;

	private DBUtils dbutils = null;

	private MovieRepositoryImpl() throws IOException {
		dbutils = DBUtils.getInstance();
	}

	public static MovieRepository getInstance() throws IOException {
		if (movieRepository == null)
			movieRepository = new MovieRepositoryImpl();
		return movieRepository;
	}

	@Override
	public String addMovie(Movie movie) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "INSERT INTO movie "
				+ "(id, name, agelimit, genere, length, releaseDate, cast, language, trailer) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(insertQuery);
			ps.setString(1, movie.getId());
			ps.setString(2, movie.getName());
			ps.setInt(3, movie.getAgelimit());
			ps.setString(4, movie.getGenere().toString());
			ps.setInt(5, movie.getLength());
			ps.setDate(6, new Date(movie.getReleaseDate().getTime()));
			ps.setString(7, String.join(",", movie.getCast()));
			ps.setString(8, movie.getLanguage().toString());
			ps.setString(9, movie.getTrailer());

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
	public String updateMovieById(String id, Movie movie) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "UPDATE movie SET name = ?, agelimit = ?, genere = ?, length = ?, "
				+ "releaseDate = ?, cast = ?, language = ?, trailer = ? where id = ?";

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(insertQuery);
			ps.setString(1, movie.getName());
			ps.setInt(2, movie.getAgelimit());
			ps.setString(3, movie.getGenere());
			ps.setInt(4, movie.getLength());
			ps.setDate(5, new Date(movie.getReleaseDate().getTime()));
			ps.setString(6, String.join(",", movie.getCast()));
			ps.setString(7, movie.getLanguage());
			ps.setString(8, movie.getTrailer());
			ps.setString(9, movie.getId());

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
	public String deleteMovieById(String id) {
		Connection connection = dbutils.getConnection();
		String delQuery = "DELETE FROM movie where id=?";
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
	public Optional<Movie> getMovieById(String id) throws InvalidIdLengthException {
		Connection connection = dbutils.getConnection();

		String getQuery = "SELECT * FROM movie where id=?";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			prepStatement.setString(1, id);
			ResultSet result = prepStatement.executeQuery();

			if (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getString("id"));
				movie.setName(result.getString("name"));
				movie.setAgelimit(result.getInt("agelimit"));
				movie.setGenere(result.getString("genere"));
				movie.setLength(result.getInt("length"));
				movie.setReleaseDate(result.getDate("releaseDate"));
				movie.setCast(result.getString("cast").split(","));
				movie.setLanguage(result.getString("language"));
				movie.setTrailer(result.getString("trailer"));
				return Optional.of(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}

		return Optional.empty();
	}

	@Override
	public List<Movie> getAllMoviesList() throws InvalidIdLengthException {
		List<Movie> movies = new ArrayList<>();
		Connection connection = dbutils.getConnection();

		String getQuery = "SELECT * FROM movie";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getString("id"));
				movie.setName(result.getString("name"));
				movie.setAgelimit(result.getInt("agelimit"));
				movie.setGenere(result.getString("genere"));
				movie.setLength(result.getInt("length"));
				movie.setReleaseDate(result.getDate("releaseDate"));
				movie.setCast(result.getString("cast").split(","));
				movie.setLanguage(result.getString("language"));
				movie.setTrailer(result.getString("trailer"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return movies;
	}

	@Override
	public Movie[] getAllMovie() throws InvalidIdLengthException {
		List<Movie> movies = this.getAllMoviesList();
		return movies.toArray(new Movie[movies.size()]);
	}

	@Override
	public List<Movie> getMovieByName(String name) throws InvalidIdLengthException {
		List<Movie> movies = new ArrayList<>();
		Connection connection = dbutils.getConnection();

		String getQuery = "SELECT * FROM movie WHERE name=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			prepStatement.setString(1, name);
			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getString("id"));
				movie.setName(result.getString("name"));
				movie.setAgelimit(result.getInt("agelimit"));
				movie.setGenere(result.getString("genere"));
				movie.setLength(result.getInt("length"));
				movie.setReleaseDate(result.getDate("releaseDate"));
				movie.setCast(result.getString("cast").split(","));
				movie.setLanguage(result.getString("language"));
				movie.setTrailer(result.getString("trailer"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return movies;
	}
}
