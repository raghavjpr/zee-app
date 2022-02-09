package com.zee.zee5app;

import java.io.IOException;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.enums.AUTORENEWAL;
import com.zee.zee5app.dto.enums.PAYMENTMODE;
import com.zee.zee5app.dto.enums.STATUS;
import com.zee.zee5app.dto.enums.TYPE;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.impl.SubscriptionServiceImpl;

public class MainSubscription {

	public static void main(String[] args) {

		SubscriptionService subscriptionService = null;

		try {
			subscriptionService = SubscriptionServiceImpl.getInstance();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.print("Adding sub0000003: ");
		try {
			System.out.println(subscriptionService
					.addSubscription(new Subscription("sub0000003", new java.util.Date(), new java.util.Date(), 699,
							PAYMENTMODE.UPI, STATUS.ACTIVE, TYPE.MONTHLY, AUTORENEWAL.TRUE, "rg0000003")));
		} catch (InvalidAmountException | InvalidIdLengthException e) {
			e.printStackTrace();
		}

		// Checking the
		System.out.print("Searching sb00003: ");
		try {
			System.out.println(subscriptionService.getSubscriptionById("sb00003").isPresent());
		} catch (InvalidIdLengthException | InvalidAmountException e) {
			e.printStackTrace();
		}

		// Valid Object
		Subscription subscription2 = null;
		try {
			subscription2 = new Subscription("sub0000003", new java.util.Date(), new java.util.Date(), 799,
					PAYMENTMODE.UPI, STATUS.INACTIVE, TYPE.QUARTERLY, AUTORENEWAL.FALSE, "rg0000003");

		} catch (InvalidAmountException | InvalidIdLengthException e) {
			e.printStackTrace();
		}

		// Updating the object
		System.out.print("Updating sub0000003: ");
		System.out.println(subscriptionService.updateSubscriptionById("sub0000003", subscription2));

		// Deleting the object
		System.out.print("Deleting sub00003: ");
		System.out.println(subscriptionService.deleteSubscriptionById("sub00003"));

		// Checking the
		System.out.print("Searching sub0000003: ");
		try {
			System.out.println(subscriptionService.getSubscriptionById("sub0000003").isPresent());
		} catch (InvalidIdLengthException | InvalidAmountException e) {
			e.printStackTrace();
		}

		System.out.println("Subscriptions List: ");
		try {
			subscriptionService.getAllSubscriptionsList().forEach(Subscription -> System.out.println(Subscription));
		} catch (InvalidIdLengthException | InvalidAmountException e) {
			e.printStackTrace();
		}

		System.out.println("Subscriptions Array: ");
		try {
			for (Subscription Subscription : subscriptionService.getAllSubscriptions())
				System.out.println(Subscription);
		} catch (InvalidIdLengthException | InvalidAmountException e) {
			e.printStackTrace();
		}

	}
}