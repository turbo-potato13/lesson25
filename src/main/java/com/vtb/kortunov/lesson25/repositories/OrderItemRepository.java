package com.vtb.kortunov.lesson25.repositories;

import com.vtb.kortunov.lesson25.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
