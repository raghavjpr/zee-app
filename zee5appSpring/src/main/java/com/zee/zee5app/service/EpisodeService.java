package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.InvalidIdLengthException;

public interface EpisodeService {
	public String addEpisode(Episode episode) throws InvalidIdLengthException;

	public String updateEpisodeById(String id, Episode episode);

	public String deleteEpisodeById(String id) throws InvalidIdLengthException;

	public Optional<Episode> getEpisodeById(String id) throws InvalidIdLengthException;

	public List<Episode> getAllEpisodeList() throws InvalidIdLengthException;

	public Episode[] getAllEpisode() throws InvalidIdLengthException;
}
