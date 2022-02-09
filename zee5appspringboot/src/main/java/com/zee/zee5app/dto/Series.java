package com.zee.zee5app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "seriesName") }, name = "series")
public class Series {

	@Id
	@Column(name = "seriesId")
	private String id;

	@Size(max = 50)
	@NotBlank
	private String seriesName;

	@Max(value = 70)
	@Min(value = 2)
	private int ageLimit;

	@NotBlank
	private String genere;

	@NotNull
	private Date releaseDate;

	@NotBlank
	private String cast;

	@NotBlank
	private String language;

	@Min(value = 1)
	private int numberOfEpisodes;

	@NotBlank
	private String trailer;
	
	@OneToMany(mappedBy = "series", cascade = CascadeType.ALL )
	private List<Episode> episodes = new ArrayList<Episode>();

}