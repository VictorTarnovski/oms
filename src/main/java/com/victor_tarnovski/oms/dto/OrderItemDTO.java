package com.victor_tarnovski.oms.dto;

import java.math.BigDecimal;

public record OrderItemDTO(String product, BigDecimal unitPrice, int quantity, BigDecimal totalPrice) {
}
