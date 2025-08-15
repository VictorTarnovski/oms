package com.victor_tarnovski.oms.service;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.victor_tarnovski.oms.dto.CreateCustomerDTO;
import com.victor_tarnovski.oms.dto.CustomerDTO;
import com.victor_tarnovski.oms.dto.PageDTO;
import com.victor_tarnovski.oms.mapper.CustomerMapper;
import com.victor_tarnovski.oms.repository.CustomerRepository;

import jakarta.inject.Inject;

@Service
public class CustomerService {
    private final CustomerMapper mapper;
    private final CustomerRepository repository;

    @Inject
    public CustomerService(
        final CustomerMapper mapper,
        final CustomerRepository repository
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CustomerDTO create(CreateCustomerDTO dto) {
        var model = mapper.toModel(dto);
        model = repository.save(model);
        return mapper.toDTO(model);
    }

    public PageDTO<CustomerDTO> find(int pageNumber, int pageSize) {
        var page = repository
            .findAll(PageRequest.of(pageNumber, pageSize))
            .map(mapper::toDTO);
        
        return new PageDTO<>(
            page.getTotalElements(), 
            page.getPageable().getPageNumber() + 1, 
            page.getPageable().getPageSize(), 
            page.getContent()
        );
    }

    public Optional<CustomerDTO> findOne(Long id) {
        return repository
            .findById(id)
            .map(mapper::toDTO);
    }
}
