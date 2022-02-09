package com.zee.zee5app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class Episodes {
	
	private String episode_id;
	private String series_id;
	private String episode_name;
	private String episode_length;
	private String location;
	private String trailer;
	
	public Episodes(String episode_id, String series_id, String episode_name, String episode_length, String location,
			String trailer) {
		super();
		this.episode_id = episode_id;
		this.series_id = series_id;
		this.episode_name = episode_name;
		this.episode_length = episode_length;
		this.location = location;
		this.trailer = trailer;
	}
	
	

}
