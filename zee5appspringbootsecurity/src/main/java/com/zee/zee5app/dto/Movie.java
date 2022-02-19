package com.zee.zee5app.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "movieName") })
public class Movie {

	@Id
	private String movieId;

	@Size(max = 50)
	@NotBlank
	private String movieName;

	@Max(value = 70)
	@Min(value = 2)
	private int ageLimit;

	@NotBlank
	private String genere;

	private int length;

	@NotNull
	private Date releaseDate;

	@NotBlank
	private String cast;

	@NotBlank
	private String language;

//	@Lob
//	private byte[] trailer;
	private String trailer;

}
