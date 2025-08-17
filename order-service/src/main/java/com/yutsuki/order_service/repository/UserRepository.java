package com.yutsuki.order_service.repository;

import com.yutsuki.order_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User ,Long> {
}
