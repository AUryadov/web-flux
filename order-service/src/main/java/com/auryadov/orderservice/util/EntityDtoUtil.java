package com.auryadov.orderservice.util;

import com.auryadov.orderservice.dto.OrderStatus;
import com.auryadov.orderservice.dto.PurchaseOrderResponseDto;
import com.auryadov.orderservice.dto.RequestContext;
import com.auryadov.orderservice.dto.TransactionRequestDto;
import com.auryadov.orderservice.dto.TransactionStatus;
import com.auryadov.orderservice.entity.PurchaseOrder;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static void setTransactionRequestDto(RequestContext context) {
        var dto = new TransactionRequestDto();
        dto.setUserId(context.getPurchaseOrderRequestDto().getUserId());
        dto.setAmount(context.getProductDto().getPrice());
        context.setTransactionRequestDto(dto);
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext context) {
        var order = new PurchaseOrder();
        order.setUserId(context.getPurchaseOrderRequestDto().getUserId());
        order.setProductId(context.getPurchaseOrderRequestDto().getProductId());
        order.setAmount(context.getProductDto().getPrice());
        var status = context.getTransactionResponseDto().getStatus();
        var orderStatus = TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAIL;
        order.setStatus(orderStatus);
        return order;
    }

    public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder order) {
        var dto = new PurchaseOrderResponseDto();
        BeanUtils.copyProperties(order, dto);
        dto.setOrderId(order.getId());
        return dto;
    }
}
