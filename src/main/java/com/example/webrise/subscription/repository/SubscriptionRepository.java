package com.example.webrise.subscription.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.webrise.subscription.model.Subscription;
import com.example.webrise.user.model.User;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    List<Subscription> findSubscriptionByUser(User user);

    void deleteSubscriptionByIdAndUser(Long subscriptionId, User user);

    @Query("SELECT s, COUNT(u) as userCount " +
            "FROM Subscription s JOIN s.user u " +
            "GROUP BY s " +
            "ORDER BY userCount DESC")
    List<Subscription> findTop(Pageable page);
}
