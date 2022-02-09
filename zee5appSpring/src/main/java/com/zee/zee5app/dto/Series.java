package com.zee.zee5app.dto;

import java.util.Date;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Series {

	@Setter(value = AccessLevel.NONE)
	private String id;
	private String name;
	private int agelimit;
	private String genere;
	private int length;
	private Date releasedate;
	private String[] cast;
	private String language;
	private int noofepisodes;
	private String trailer;

	public Series(String id, String name, int agelimit, String genere, int length, Date releasedate, String[] cast,
			String language, int noofepisodes, String trailer) throws InvalidIdLengthException {
		super();
		this.setId(id);
		this.name = name;
		this.agelimit = agelimit;
		this.genere = genere;
		this.length = length;
		this.releasedate = releasedate;
		this.cast = cast;
		this.language = language;
		this.noofepisodes = noofepisodes;
		this.trailer = trailer;
	}

	public void setId(String id) throws InvalidIdLengthException {
		if (id.length() < 6) {
			throw new InvalidIdLengthException("INVALID ID LENGTH ==> Wrong ID:" + id
					+ ". Minimum Length Required:6. Length Given:" + id.length());
		}
		this.id = id;
	}

}