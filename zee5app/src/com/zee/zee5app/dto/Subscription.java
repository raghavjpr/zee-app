package com.zee.zee5app.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private String date_of_purchase;
	private String expiry_date;
	@Setter(value = AccessLevel.NONE)
	private String amount;
	private String payment_mode;
	private String status;
	private String type;
	private String autorenewal;

	
	public Subscription(String id, String type, String date_of_purchase, String status, String amount,
			String payment_mode, String autorenewal, String expiry_date) throws InvalidIdLengthException, InvalidAmountException {
		super();
		this.setId(id);
		this.type = type;
		this.date_of_purchase = date_of_purchase;
		this.status = status;
		this.setAmount(amount);
		this.payment_mode = payment_mode;
		this.autorenewal = autorenewal;
		this.expiry_date = expiry_date;
	}
	
	public void setId(String id) throws InvalidIdLengthException {
		if (id.length() < 6) {
			throw new InvalidIdLengthException("INVALID ID LENGTH ==> Wrong ID:" + id +". Minimum Length Required:6. Length Given:" + id.length());
		}
		this.id = id;
	}
	
	public void setAmount(String amount) throws InvalidAmountException {
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(amount);
		if (!m.matches()) {
			throw new InvalidAmountException("INVALID AMOUNT ==> Amount contains letters.");
		}
		this.amount = amount;
	}
	
	
	
}
