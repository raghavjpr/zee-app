package com.zee.zee5app.dto;

import java.util.Date;

import com.zee.zee5app.dto.enums.AUTORENEWAL;
import com.zee.zee5app.dto.enums.PAYMENTMODE;
import com.zee.zee5app.dto.enums.STATUS;
import com.zee.zee5app.dto.enums.TYPE;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor

public class Subscription {

	@Setter(value = AccessLevel.NONE)
	private String id;
	private Date dateOfPurchase;
	private Date expiryDate;
	@Setter(value = AccessLevel.NONE)
	private float amount;
	private PAYMENTMODE paymentmode;
	private STATUS status;
	private TYPE type;
	private AUTORENEWAL autorenewal;
	@Setter(value = AccessLevel.NONE)
	private String regId;

	public Subscription(String id, Date dateOfPurchase, Date expiryDate, float amount, PAYMENTMODE paymentmode,
			STATUS status, TYPE type, AUTORENEWAL autorenewal, String regId) throws InvalidIdLengthException, InvalidAmountException {
		super();
		this.setId(id);
		this.dateOfPurchase = dateOfPurchase;
		this.expiryDate = expiryDate;
		this.setAmount(amount);
		this.paymentmode = paymentmode;
		this.status = status;
		this.type = type;
		this.autorenewal = autorenewal;
		//this.setRegId(regId);
	}

	public void setId(String id) throws InvalidIdLengthException {
		if (id.length() < 6) {
			throw new InvalidIdLengthException("INVALID ID LENGTH ==> Wrong ID:" + id
					+ ". Minimum Length Required:6. Length Given:" + id.length());
		}
		this.id = id;
	}

	public void setRegId(String regId) throws InvalidIdLengthException {
		if (regId.length() < 6) {
			throw new InvalidIdLengthException("INVALID ID LENGTH ==> Wrong ID:" + regId
					+ ". Minimum Length Required:6. Length Given:" + regId.length());
		}
		this.regId = regId;
	}

	public void setAmount(float amount) throws InvalidAmountException {
		if (amount < 599) {
			throw new InvalidAmountException("Invalid Amount");
		}
		this.amount = amount;
	}

}
