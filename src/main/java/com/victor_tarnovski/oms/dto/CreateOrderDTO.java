package com.victor_tarnovski.oms.dto;

import java.util.List;

public record CreateOrderDTO(String code, Long customerId, List<CreateOrderItemDTO> items) {
}
