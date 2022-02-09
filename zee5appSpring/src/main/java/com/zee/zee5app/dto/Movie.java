package com.zee.zee5app.dto;

import java.sql.Date;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Movie {

	@Setter(value = AccessLevel.NONE)
	private String id;
	private String name;
	private int agelimit;
	private String genere;
	private int length;
	private Date releaseDate;
	private String[] cast;
	private String language;
	private String trailer;

	public Movie(String id, String name, int agelimit, String genere, int length, Date releaseDate, String[] cast,
			String language, String trailer) throws InvalidIdLengthException {
		super();
		this.setId(id);
		this.name = name;
		this.agelimit = agelimit;
		this.genere = genere;
		this.length = length;
		this.releaseDate = releaseDate;
		this.cast = cast;
		this.language = language;
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
