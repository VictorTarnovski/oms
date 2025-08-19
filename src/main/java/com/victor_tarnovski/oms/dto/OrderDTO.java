package com.victor_tarnovski.oms.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(Long id, String code, BigDecimal totalPrice, CustomerDTO customer, List<OrderItemDTO> items) {
}
