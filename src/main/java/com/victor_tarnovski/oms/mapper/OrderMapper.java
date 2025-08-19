package com.victor_tarnovski.oms.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.victor_tarnovski.oms.dto.CreateOrderDTO;
import com.victor_tarnovski.oms.dto.CreateOrderItemDTO;
import com.victor_tarnovski.oms.dto.OrderDTO;
import com.victor_tarnovski.oms.dto.OrderItemDTO;
import com.victor_tarnovski.oms.model.Customer;
import com.victor_tarnovski.oms.model.Order;
import com.victor_tarnovski.oms.model.OrderItem;

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
        model.setItems(toModel(dto.items(), model));
        
        return model;
    }

    private List<OrderItem> toModel(List<CreateOrderItemDTO> dtos, Order order) {
        return dtos.stream()
            .map(dto -> {
                return this.toModel(dto, order);
            })
            .toList();
    }

    private OrderItem toModel(CreateOrderItemDTO dto, Order order) {
        var model = new OrderItem();

        model.setProduct(dto.product());
        model.setUnitPrice(dto.unitPrice());
        model.setQuantity(dto.quantity());
        model.setOrder(order);

        return model;
    }

    public OrderDTO toDTO(Order model) {
        return new OrderDTO(
            model.getId(),
            model.getCode(),
            model.getTotalPrice(),
            customerMapper.toDTO(model.getCustomer()),
            this.toDTO(model.getItems())
        );
    }

    private List<OrderItemDTO> toDTO(List<OrderItem> models) {
        return models.stream()
            .map(this::toDTO)
            .toList();
    }

    private OrderItemDTO toDTO(OrderItem model) {
        return new OrderItemDTO(
            model.getProduct(),
            model.getUnitPrice(),
            model.getQuantity(),
            model.getTotalPrice()
        );
    }
}
