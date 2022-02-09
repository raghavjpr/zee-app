package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
	private EpisodeRepository episodeRepository;

	@Override
	public String addEpisode(Episode episode) throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.episodeRepository.addEpisode(episode);
	}

	@Override
	public String updateEpisodeById(String id, Episode episode) {
		// TODO Auto-generated method stub
		return this.episodeRepository.updateEpisodeById(id, episode);
	}

	@Override
	public String deleteEpisodeById(String id) throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.episodeRepository.deleteEpisodeById(id);
	}

	@Override
	public Optional<Episode> getEpisodeById(String id) throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.episodeRepository.getEpisodeById(id);
	}

	@Override
	public List<Episode> getAllEpisodeList() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.episodeRepository.getAllEpisodeList();
	}

	@Override
	public Episode[] getAllEpisode() throws InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.episodeRepository.getAllEpisode();
	}
}
