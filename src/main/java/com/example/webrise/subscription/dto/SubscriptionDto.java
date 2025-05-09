package com.example.webrise.subscription.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SubscriptionDto {
    private Long id;
    private String subscriptionName;
    private Integer isActive;
}
