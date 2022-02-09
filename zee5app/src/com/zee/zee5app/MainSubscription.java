package com.zee.zee5app;


import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.impl.SubscriptionServiceImpl;

public class MainSubscription {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Subscription subscription1 = null, subscription2 = null, subscription3 = null, subscription4 = null, subscription5 = null, subscription6 = null, subscription7 = null;
		
		try {
			subscription1 = new Subscription("00001", null, null, null, "1", null, null, null);
		} catch (InvalidIdLengthException | InvalidAmountException e) {
			// TODO Auto-generated catch block
			try {
				subscription1 = new Subscription("000001", null, null, null, "1", null, null, null);
			} catch (InvalidIdLengthException | InvalidAmountException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		try {
			subscription2 = new Subscription("000002", null, null, null, "2test", null, null, null);
		} catch (InvalidAmountException | InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			try {
				subscription2 = new Subscription("000002", null, null, null, "2", null, null, null);
			} catch (InvalidAmountException | InvalidIdLengthException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		
			try {
				subscription3 = new Subscription("000003", null, null, null, "3", null, null, null);
				subscription4 = new Subscription("000004", null, null, null, "4", null, null, null);
				subscription5 = new Subscription("000005", null, null, null, "5", null, null, null);
				subscription6 = new Subscription("000006", null, null, null, "6", null, null, null);
				subscription7 = new Subscription("000007", null, null, null, "7", null, null, null);
			} catch (InvalidIdLengthException | InvalidAmountException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}		
		
		System.out.println(subscription1);
		System.out.println(subscription2);
		System.out.println(subscription3);
		System.out.println(subscription4);
		System.out.println(subscription5);
		System.out.println(subscription6);
		
		SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();
		
		System.out.println("		***Adding subscription1, subscription2, subscription3, subscription4, subscription5 & subscription6.***");
		System.out.println(subscriptionService.addSubscription(subscription1));
		System.out.println(subscriptionService.addSubscription(subscription2));
		System.out.println(subscriptionService.addSubscription(subscription3));
		System.out.println(subscriptionService.addSubscription(subscription4));
		System.out.println(subscriptionService.addSubscription(subscription5));
		System.out.println(subscriptionService.addSubscription(subscription6));
		
		System.out.println("		***It is ArrayList.***");
		subscriptionService.getAllSubscription().forEach(e->System.out.println(e));
		
		System.out.println("		***Trying to add subscription1 & subscription4 once more.***");
		System.out.println(subscriptionService.addSubscription(subscription1));
		System.out.println(subscriptionService.addSubscription(subscription4));
		
		subscriptionService.getAllSubscription().forEach(e->System.out.println(e));
		
		System.out.println("		***Updating subscription6 with subscription7.***");
		try {
			System.out.println(subscriptionService.updateSubscription("000006", subscription7));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("		***Updating subscription6 with subscription7 again.***");
		try {
			System.out.println(subscriptionService.updateSubscription("000006", subscription7));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		subscriptionService.getAllSubscription().forEach(e->System.out.println(e));
		
		System.out.println("		***Deleting subscription7 which is present.***");
		try {
			System.out.println(subscriptionService.deleteSubscriptionById("000007"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("		***Deleting subscription6 which is not present.***");
		try {
			System.out.println(subscriptionService.deleteSubscriptionById("000006"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		subscriptionService.getAllSubscription().forEach(e->System.out.println(e));
		
		System.out.println("		***Getting subscription with id 000004(Present).***");
		try {
			Subscription subscription = subscriptionService.getSubscriptionById("000004");
			System.out.println(subscription);
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting subscription with id 000008(Not Present).***");
		try {
			Subscription subscription = subscriptionService.getSubscriptionById("000008");
			System.out.println(subscription);
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		subscriptionService.getAllSubscription().forEach(e->System.out.println(e));
	}
}