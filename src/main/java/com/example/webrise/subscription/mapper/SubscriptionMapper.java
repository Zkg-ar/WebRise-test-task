package com.example.webrise.subscription.mapper;

import com.example.webrise.subscription.dto.SubscriptionDto;
import com.example.webrise.subscription.model.Subscription;
import com.example.webrise.user.model.User;


public class SubscriptionMapper {
    public static SubscriptionDto mapToDto(Subscription subscription) {
        return SubscriptionDto
                .builder()
                .id(subscription.getId())
                .subscriptionName(subscription.getSubscriptionName())
                .isActive(subscription.getIsActive())
                .build();

    }

    public static Subscription mapToModel(SubscriptionDto subscriptionDto, User user) {
        Subscription subscription = new Subscription();
        subscription.setId(subscription.getId());
        subscription.setIsActive(subscriptionDto.getIsActive());
        subscription.setUser(user);
        subscription.setSubscriptionName(subscriptionDto.getSubscriptionName());
        return subscription;
    }
}
