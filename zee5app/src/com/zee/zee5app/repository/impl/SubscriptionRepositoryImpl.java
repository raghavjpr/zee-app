package com.zee.zee5app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SubscriptionRepository;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {
	
	private SubscriptionRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private static SubscriptionRepository subscriptionRepository ;
	public static SubscriptionRepository getInstace() {
		if(subscriptionRepository == null)
			subscriptionRepository = new SubscriptionRepositoryImpl();
		return subscriptionRepository;
	}
	
	private List<Subscription> subscriptionList = new ArrayList<Subscription>();

	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		boolean result = this.subscriptionList.add(subscription);
		if (result) {
			return "SUCCESS ==> Subscription successfully added in  ArrayList.";
		}
		return "FAILED ==> Movie addition was not successfull.";
	}

	@Override
	public String updateSubscription(String id, Subscription subscription) throws IdNotFoundException {
		Subscription subscriptionTemp = this.getSubscriptionById(id);
		if (subscriptionTemp != null) {
			int itemIndex = subscriptionList.indexOf(subscriptionTemp);
			subscriptionList.set(itemIndex, subscription);
			return "SUCCESS ==> Subscription Successfully Updated";
		}
		return "FAILED ==> No element to update";
	}

	@Override
	public Subscription getSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Subscription subscription = null;
		for (Subscription subscriptionTemp : subscriptionList) {
			if(subscriptionTemp!=null && subscriptionTemp.getId().equals(id) ) {
				//return subscriptionTemp;
				subscription = subscriptionTemp;
			}
		}
		if (subscription == null) {
			throw new IdNotFoundException("FAILED ==> No suscription with such ID.");
		}
		return subscription;
	}

	@Override
	public List<Subscription> getAllSubscription() {
		// TODO Auto-generated method stub
		return subscriptionList;
	}

	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Subscription subscriptionTemp = this.getSubscriptionById(id);
		if (subscriptionTemp != null) {
			this.subscriptionList.remove(subscriptionTemp);
			return "SUCCESS ==> Element with " + id + " deleted.";
		}
		return "FAILED ==> No such element by " + id + " present.";
	}
}
