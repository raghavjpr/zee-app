package com.zee.zee5app.dto;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Episode {

	@Setter(value = AccessLevel.NONE)
	private String epiId;
	@Setter(value = AccessLevel.NONE)
	private String seriesId;
	private String episodename;
	private int length;
	private String location;
	//private String trailer;

	public Episode(String epiId, String seriesId, String episodename, int length, String location)
			throws InvalidIdLengthException {
		super();
		this.setEpisode_id(epiId);
		this.setSeries_id(seriesId);
		this.episodename = episodename;
		this.length = length;
		this.location = location;
	}

	public void setEpisode_id(String epiId) throws InvalidIdLengthException {
		if (epiId.length() < 6) {
			throw new InvalidIdLengthException("INVALID ID LENGTH ==> Wrong ID:" + epiId
					+ ". Minimum Length Required:6. Length Given:" + epiId.length());
		}
		this.epiId = epiId;
	}

	public void setSeries_id(String seriesId) throws InvalidIdLengthException {
		if (seriesId.length() < 6) {
			throw new InvalidIdLengthException("INVALID ID LENGTH ==> Wrong ID:" + seriesId
					+ ". Minimum Length Required:6. Length Given:" + seriesId.length());
		}
		this.seriesId = seriesId;
	}

}
