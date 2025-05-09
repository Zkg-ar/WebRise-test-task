package com.example.webrise.subscription.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.webrise.subscription.dto.SubscriptionDto;
import com.example.webrise.subscription.service.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{id}/subscriptions")
    public SubscriptionDto create(@Valid @RequestBody SubscriptionDto subscriptionDto,
                                  @PathVariable(name = "id") Long userId) {
        return subscriptionService.create(subscriptionDto, userId);
    }

    @GetMapping("/users/{id}/subscriptions")
    public List<SubscriptionDto> getById(@PathVariable(name = "id") Long userId) {
        return subscriptionService.getById(userId);
    }

    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public void deleteSubscription(@PathVariable(name = "id") Long userId,
                                   @PathVariable(name = "sub_id") Long subscriptionId) {
        subscriptionService.deleteSubscription(userId, subscriptionId);
    }

    @GetMapping("/subscriptions/top")
    public List<SubscriptionDto> getSubscriptionTop() {
        return subscriptionService.getSubscriptionTop();
    }


}
