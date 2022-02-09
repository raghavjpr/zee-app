package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
	EpisodeRepository episodeRepository;

	@Override
	public String addEpisode(Episode episode) {
		Episode episode2 = episodeRepository.save(episode);
		if (episode2 != null) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String deleteEpisodeById(String id) throws IdNotFoundException {
		Optional<Episode> optional = episodeRepository.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No Record Found");
		} else {
			episodeRepository.deleteById(id);
			return "success";
		}
	}

	@Override
	public Optional<Episode> getEpisodeById(String id) {
		return episodeRepository.findById(id);
	}

	@Override
	public List<Episode> getAllEpisodeList() {
		return episodeRepository.findAll();
	}

	@Override
	public Episode[] getAllEpisode() {
		List<Episode> list = episodeRepository.findAll();
		Episode[] array = new Episode[list.size()];
		return list.toArray(array);
	}

}
