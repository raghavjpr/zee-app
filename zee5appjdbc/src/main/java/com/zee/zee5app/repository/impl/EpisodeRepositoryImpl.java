package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5pp.utils.DBUtils;

public class EpisodeRepositoryImpl implements EpisodeRepository {
	private static EpisodeRepository episodeRepository = null;
	private DBUtils dbutils = null;
	private SeriesRepository seriesRepository = null;

	private EpisodeRepositoryImpl() throws IOException {
		dbutils = DBUtils.getInstance();
		seriesRepository = SeriesRepositoryImpl.getInstance();
	}

	public static EpisodeRepository getInstance() throws IOException {
		if (episodeRepository == null)
			episodeRepository = new EpisodeRepositoryImpl();
		return episodeRepository;
	}

	@Override
	public String addEpisode(Episode episode) throws InvalidIdLengthException {
		Series series;
		series = seriesRepository.getSeriesById(episode.getSeriesId()).get();
		Connection connection = dbutils.getConnection();
		String insertQuery = "INSERT INTO episode " + "(epiId, serialId, episodename, epilength, location) "
				+ "VALUES (?,?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setString(1, episode.getEpiId());
			ps.setString(2, episode.getSeriesId());
			ps.setString(3, episode.getEpisodename());
			ps.setInt(4, episode.getLength());
			ps.setString(5, episode.getLocation());

			int result = ps.executeUpdate();
			if (result > 0) {
				series.setNoofepisodes(series.getNoofepisodes() + 1);
				return seriesRepository.updateSeriesById(episode.getSeriesId(), series);
			}
			connection.rollback();
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
	public String updateEpisodeById(String id, Episode episode) {
		Connection connection = dbutils.getConnection();
		String insertQuery = "UPDATE episode SET " + "episodename=?, epilength=?, location=? " + "WHERE epiId=?";

		try {
			PreparedStatement ps = connection.prepareStatement(insertQuery);
			ps.setString(1, episode.getEpisodename());
			ps.setInt(2, episode.getLength());
			ps.setString(3, episode.getLocation());
			ps.setString(4, episode.getEpiId());

			int result = ps.executeUpdate();

			if (result > 0) {
				connection.commit();
				return "fail";
			}
			connection.rollback();
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
	public String deleteEpisodeById(String id) throws InvalidIdLengthException {
		Series series;
		Optional<Episode> episode = this.getEpisodeById(id);
		if (episode.isEmpty())
			return "fail";
		series = seriesRepository.getSeriesById(episode.get().getSeriesId()).get();
		Connection connection = dbutils.getConnection();
		String delQuery = "DELETE FROM episode where epiId=?";
		try {

			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);
			int result = prepStatement.executeUpdate();
			if (result > 0) {
				series.setNoofepisodes(series.getNoofepisodes() - 1);
				return seriesRepository.updateSeriesById(episode.get().getSeriesId(), series);
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
	public Optional<Episode> getEpisodeById(String id) throws InvalidIdLengthException {
		Connection connection = dbutils.getConnection();

		String getQuery = "SELECT * FROM episode where epiId=?";

		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			prepStatement.setString(1, id);
			ResultSet result = prepStatement.executeQuery();

			if (result.next()) {
				Episode episode = new Episode();
				episode.setEpisode_id(result.getString("epiId"));
				episode.setSeries_id(result.getString("serialId"));
				episode.setEpisodename(result.getString("episodename"));
				episode.setLength(result.getInt("epilength"));
				episode.setLocation(result.getString("location"));

				return Optional.of(episode);
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}

		return Optional.empty();
	}

	@Override
	public List<Episode> getAllEpisodeList() throws InvalidIdLengthException {
		List<Episode> episodes = new ArrayList<>();
		Connection connection = dbutils.getConnection();

		String getQuery = "SELECT * FROM episode";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(getQuery);
			ResultSet result = prepStatement.executeQuery();

			while (result.next()) {
				Episode episode = new Episode();
				episode.setEpisode_id(result.getString("epiId"));
				episode.setSeries_id(result.getString("serialId"));
				episode.setEpisodename(result.getString("episodename"));
				episode.setLength(result.getInt("epilength"));
				episode.setLocation(result.getString("location"));
				episodes.add(episode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.closeConnection(connection);
		}
		return episodes;
	}

	@Override
	public Episode[] getAllEpisode() throws InvalidIdLengthException {
		List<Episode> episode = this.getAllEpisodeList();
		return episode.toArray(new Episode[episode.size()]);
	}
}