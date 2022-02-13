package com.uryadov.productservice.util;

import com.uryadov.productservice.dto.ProductDto;
import com.uryadov.productservice.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ProductDto toDto(ProductEntity entity) {
        var dto = new ProductDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ProductEntity toEntity(ProductDto dto) {
        var entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
