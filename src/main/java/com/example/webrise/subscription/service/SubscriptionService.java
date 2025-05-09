package com.example.webrise.subscription.service;

import com.example.webrise.subscription.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDto create(SubscriptionDto subscriptionDto,Long userId);

    List<SubscriptionDto> getSubscriptionTop();

    List<SubscriptionDto> getById(Long userId);

    void deleteSubscription(Long userId, Long subscriptionId);

}
