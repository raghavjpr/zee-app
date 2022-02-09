package com.zee.zee5app.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zee.zee5app.dto.enums.AUTORENEWAL;
import com.zee.zee5app.dto.enums.PAYMENTMODE;
import com.zee.zee5app.dto.enums.STATUS;
import com.zee.zee5app.dto.enums.TYPE;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {

	@Id
	@Column(name = "subsId")
	@Setter(value = AccessLevel.NONE)
	private String id;

	@NotNull
	private Date dateOfPurchase;

	@NotNull
	private Date expiryDate;

	@NotNull
	private float amount;

	@NotBlank
	private PAYMENTMODE paymentmode;

	@NotBlank
	private STATUS status;

	@NotBlank
	private TYPE type;

	@NotBlank
	private AUTORENEWAL autorenewal;

	@OneToOne
	@JoinColumn(name = "registerId")
	private Register register;
}
