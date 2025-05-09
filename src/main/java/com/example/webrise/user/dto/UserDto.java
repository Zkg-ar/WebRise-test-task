package com.example.webrise.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import com.example.webrise.subscription.dto.SubscriptionDto;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    private List<SubscriptionDto> subscriptions = new ArrayList<>();
}
