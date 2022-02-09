package com.zee.zee5app.service.impl;

import java.util.List;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.service.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {
	
	private SubscriptionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	private static SubscriptionService subscriptionService;
	public static SubscriptionService getInstance() {
		if(subscriptionService == null)
			subscriptionService = new SubscriptionServiceImpl();
		return subscriptionService;
	}
	
	SubscriptionRepository subscriptionRepository = SubscriptionRepositoryImpl.getInstace();
	@Override
	public String addSubscription(Subscription subscription) {
		// TODO Auto-generated method stub
		return this.subscriptionRepository.addSubscription(subscription);
	}

	@Override
	public String updateSubscription(String id, Subscription subscription) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.subscriptionRepository.updateSubscription(id, subscription);
	}

	@Override
	public Subscription getSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.subscriptionRepository.getSubscriptionById(id);
	}

	@Override
	public List<Subscription> getAllSubscription() {
		// TODO Auto-generated method stub
		return this.subscriptionRepository.getAllSubscription();
	}

	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.subscriptionRepository.deleteSubscriptionById(id);
	}

}
