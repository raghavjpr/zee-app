package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Override
	public String addSubscription(Subscription subscription) {
		Subscription subscription2 = subscriptionRepository.save(subscription);
		if (subscription2 != null) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		Optional<Subscription> optional = subscriptionRepository.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No Record Found");
		} else {
			subscriptionRepository.deleteById(id);
			return "success";
		}
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String id) {
		return subscriptionRepository.findById(id);
	}

	@Override
	public List<Subscription> getAllSubscriptionsList() {
		return subscriptionRepository.findAll();
	}

	@Override
	public Subscription[] getAllSubscriptions() {
		List<Subscription> list = subscriptionRepository.findAll();
		Subscription[] array = new Subscription[list.size()];
		return list.toArray(array);
	}

}
