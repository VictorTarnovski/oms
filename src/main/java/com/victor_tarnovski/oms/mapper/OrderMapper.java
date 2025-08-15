package com.victor_tarnovski.oms.mapper;

import org.springframework.stereotype.Component;

import com.victor_tarnovski.oms.dto.CreateOrderDTO;
import com.victor_tarnovski.oms.dto.OrderDTO;
import com.victor_tarnovski.oms.model.Customer;
import com.victor_tarnovski.oms.model.Order;

@Component
public class OrderMapper {
    private final CustomerMapper customerMapper;

    public OrderMapper(final CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public Order toModel(CreateOrderDTO dto, Customer customer) {
        var model = new Order();

        model.setCode(dto.code());
        model.setCustomer(customer);

        return model;
    }

    public OrderDTO toDTO(Order model) {
        return new OrderDTO(
            model.getId(),
            model.getCode(),
            customerMapper.toDTO(model.getCustomer())
        );
    }
}
