package com.victor_tarnovski.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor_tarnovski.oms.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {    
}
