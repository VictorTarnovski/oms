package com.victor_tarnovski.oms.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public record PageDTO<T>(long total, int page, int pageSize, List<T> content) {
    public PageDTO<T> from(Page<T> page) {
        return new PageDTO<>(page.getTotalElements(), page.getPageable().getPageNumber(), page.getPageable().getPageSize(), page.getContent());
    }
};