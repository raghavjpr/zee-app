package com.zee.zee5app.repository;

import java.util.List;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;

public interface SubscriptionRepository {
	
	public String addSubscription(Subscription subscription);
	public String updateSubscription(String id, Subscription subscription) throws IdNotFoundException;
	public Subscription getSubscriptionById(String id) throws IdNotFoundException;
	public List<Subscription> getAllSubscription();
	public String deleteSubscriptionById(String id) throws IdNotFoundException;
	
	//ArrayList
	//InvalidAmountException
	
}