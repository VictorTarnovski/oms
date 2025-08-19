package com.victor_tarnovski.oms.service;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.victor_tarnovski.oms.dto.CreateOrderDTO;
import com.victor_tarnovski.oms.dto.OrderDTO;
import com.victor_tarnovski.oms.dto.PageDTO;
import com.victor_tarnovski.oms.mapper.OrderMapper;
import com.victor_tarnovski.oms.repository.CustomerRepository;
import com.victor_tarnovski.oms.repository.OrderRepository;
import com.victor_tarnovski.oms.model.Customer;

import jakarta.inject.Inject;

@Service
public class OrderService {
    private final OrderMapper mapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Inject
    public OrderService(
        final OrderMapper mapper,
        final OrderRepository orderRepository,
        final CustomerRepository customerRepository
    ) {
        this.mapper = mapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;    
    }

    public OrderDTO create(CreateOrderDTO dto) {
        var opt = customerRepository.findById(dto.customerId());
        if(opt.isEmpty()) return null;

        Customer customer = opt.get();
        var model = mapper.toModel(dto, customer);
        model = orderRepository.save(model);
        return mapper.toDTO(model);
    }

    public PageDTO<OrderDTO> find(int pageNumber, int pageSize) {
        var page = orderRepository 
            .findAll(PageRequest.of(pageNumber, pageSize))
            .map(mapper::toDTO);
        
        return new PageDTO<>(
            page.getTotalElements(), 
            page.getPageable().getPageNumber() + 1, 
            page.getPageable().getPageSize(), 
            page.getContent()
        );
    }

    public Optional<OrderDTO> findOne(Long id) {
        return orderRepository
            .findById(id)
            .map(mapper::toDTO);
    }
}
