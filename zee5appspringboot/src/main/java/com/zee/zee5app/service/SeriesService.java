package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;

public interface SeriesService {

	public String addSeries(Series series);

	public String deleteSeriesById(String id) throws IdNotFoundException;

	public Optional<Series> getSeriesById(String id);

	public List<Series> getAllSeriesList();

	public Series[] getAllSeries();
}
