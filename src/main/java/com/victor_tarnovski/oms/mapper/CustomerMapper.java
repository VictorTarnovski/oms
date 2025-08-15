package com.victor_tarnovski.oms.mapper;

import org.springframework.stereotype.Component;

import com.victor_tarnovski.oms.dto.CreateCustomerDTO;
import com.victor_tarnovski.oms.dto.CustomerDTO;
import com.victor_tarnovski.oms.model.Customer;

@Component
public class CustomerMapper {
    public Customer toModel(CreateCustomerDTO dto) {
        var model = new Customer();

        model.setDocument(dto.document());
        model.setFirstName(dto.firstName());
        model.setLastName(dto.lastName());

        return model;
    }

    public CustomerDTO toDTO(Customer model) {
        return new CustomerDTO(
            model.getId(),
            model.getDocument(),
            model.getFirstName(),
            model.getLastName()
        );
    }
}
