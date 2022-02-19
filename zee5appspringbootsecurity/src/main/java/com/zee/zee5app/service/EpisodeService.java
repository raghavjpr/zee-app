package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;

public interface EpisodeService {
	
	public String addEpisode(Episode episode);

	public String deleteEpisodeById(String id) throws IdNotFoundException;

	public Optional<Episode> getEpisodeById(String id);

	public List<Episode> getAllEpisodeList();

	public Episode[] getAllEpisode();
}
