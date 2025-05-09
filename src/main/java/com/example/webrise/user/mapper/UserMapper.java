package com.example.webrise.user.mapper;

import com.example.webrise.subscription.mapper.SubscriptionMapper;
import com.example.webrise.user.dto.UserDto;
import com.example.webrise.user.model.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static User mapToModel(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        if (userDto.getSubscriptions() != null) {
            user.setSubscriptions(userDto
                    .getSubscriptions()
                    .stream()
                    .map(item -> SubscriptionMapper.mapToModel(item, user))
                    .collect(Collectors.toList()));
        }

        return user;
    }
}
