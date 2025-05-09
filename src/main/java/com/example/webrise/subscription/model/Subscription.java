package com.example.webrise.subscription.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.webrise.user.model.User;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long id;

    @Column(name = "subscription_name", nullable = false)
    private String subscriptionName;

    @Column(name = "is_active")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
