package com.victor_tarnovski.oms.dto;

import java.math.BigDecimal;

public record CreateOrderItemDTO(String product, BigDecimal unitPrice, int quantity) {
}
