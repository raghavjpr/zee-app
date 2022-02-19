package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.service.SeriesService;

@Service
public class SeriesServiceImpl implements SeriesService {

	@Autowired
	SeriesRepository seriesRepository;

	@Override
	public String addSeries(Series series) {
		Series series2 = seriesRepository.save(series);
		if (series2 != null) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		Optional<Series> optional = seriesRepository.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No Record Found");
		} else {
			seriesRepository.deleteById(id);
			return "success";
		}
	}

	@Override
	public Optional<Series> getSeriesById(String id) {
		return seriesRepository.findById(id);
	}

	@Override
	public List<Series> getAllSeriesList() {
		return seriesRepository.findAll();
	}

	@Override
	public Series[] getAllSeries() {
		List<Series> list = seriesRepository.findAll();
		Series[] array = new Series[list.size()];
		return list.toArray(array);
	}
}
