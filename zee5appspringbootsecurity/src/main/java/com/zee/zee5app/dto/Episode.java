package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "episode")
public class Episode {

	@Id
	private String episodeID;

	@Size(max = 50)
	@NotBlank
	private String episodeName;

	private int length;

	@NotBlank
	private String location;

	@NotBlank
	private String trailer;
	
	@ManyToOne
	@JoinColumn(name = "seriesId")
	private Series series; // series id and that column should act as a foreign key column
	
	
	
}
