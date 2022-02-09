package com.zee.zee5app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.dto.enums.AUTORENEWAL;
import com.zee.zee5app.dto.enums.PAYMENTMODE;
import com.zee.zee5app.dto.enums.STATUS;
import com.zee.zee5app.dto.enums.TYPE;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.SubscriptionService;

public class MainSubscription {

	public static void main(String[] args) {

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

		SubscriptionService subscriptionService = applicationContext.getBean(SubscriptionService.class);

		System.out.print("Adding sub6030003: ");
		try {
			System.out.println(subscriptionService
					.addSubscription(new Subscription("sub6030003", new java.util.Date(), new java.util.Date(), 699,
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

		Subscription subscription = null;
		try {
			subscription = new Subscription("sub0000003", new java.util.Date(), new java.util.Date(), 799,
					PAYMENTMODE.UPI, STATUS.INACTIVE, TYPE.QUARTERLY, AUTORENEWAL.FALSE, "rg0000003");

		} catch (InvalidAmountException | InvalidIdLengthException e) {
			e.printStackTrace();
		}

		// Updating the object
		System.out.print("Updating sub0000003: ");
		System.out.println(subscriptionService.updateSubscriptionById("sub0000003", subscription));

		// Deleting the object
		System.out.print("Deleting sb00002: ");
		System.out.println(subscriptionService.deleteSubscriptionById("sb00002"));

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

		applicationContext.close();

	}
}