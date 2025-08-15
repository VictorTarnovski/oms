package com.victor_tarnovski.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor_tarnovski.oms.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {    
}
