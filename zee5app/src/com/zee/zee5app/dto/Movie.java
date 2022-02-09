package com.zee.zee5app.dto;

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
	private String movie_name;
	private String ageLimit;
	private String[] cast;
	private String genere;
	private String length;
	private String trailer;
	private String release_date;
	private String language;
	
	private String location;
	
	public Movie(String id, String movie_name, String location, String release_date, String trailer, String language,
			String[] cast, String length) throws InvalidIdLengthException {
		super();
		this.setId(id);
		this.movie_name = movie_name;
		this.location = location;
		this.release_date = release_date;
		this.trailer = trailer;
		this.language = language;
		this.cast = cast;
		this.length = length;
	}
	
	public void setId(String id) throws InvalidIdLengthException {
		if (id.length() < 6) {
			throw new InvalidIdLengthException("INVALID ID LENGTH ==> Wrong ID:" + id +". Minimum Length Required:6. Length Given:" + id.length());
		}
		this.id = id;
	}	
}
