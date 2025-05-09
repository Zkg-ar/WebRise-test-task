package com.example.webrise.subscription.service;

import com.example.webrise.exceptions.NotFoundException;
import com.example.webrise.subscription.model.Subscription;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.webrise.subscription.dto.SubscriptionDto;
import com.example.webrise.subscription.mapper.SubscriptionMapper;
import com.example.webrise.subscription.repository.SubscriptionRepository;
import com.example.webrise.user.mapper.UserMapper;
import com.example.webrise.user.model.User;
import com.example.webrise.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Override
    public SubscriptionDto create(SubscriptionDto subscriptionDto, Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %d не найден", userId)));
        Subscription subscription = SubscriptionMapper.mapToModel(subscriptionDto,user);
        return SubscriptionMapper.mapToDto(subscriptionRepository.save(subscription));
    }

    @Override
    public List<SubscriptionDto> getSubscriptionTop() {
        Pageable pageable = PageRequest.of(0, 3);
        return subscriptionRepository.findTop(pageable)
                .stream()
                .map(SubscriptionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<SubscriptionDto> getById(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %d не найден", userId)));
        return subscriptionRepository.findSubscriptionByUser(user)
                .stream()
                .map(SubscriptionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubscription(Long userId, Long subscriptionId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %d не найден", userId)));
        subscriptionRepository.deleteSubscriptionByIdAndUser(subscriptionId, user);
    }
}
