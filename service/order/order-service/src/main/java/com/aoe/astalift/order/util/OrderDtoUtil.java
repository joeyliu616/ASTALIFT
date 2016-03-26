package com.aoe.astalift.order.util;

import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.entity.Order;

/**
 * Created by joey on 16-3-24.
 */
public class OrderDtoUtil {

    public static OrderInfoDto getOrderInfoDto(Order order, ProfileInfo buyerProfile, ProfileInfo supplierProfile){
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setStatusCode(order.getCurrentStatus());
        orderInfoDto.setStatusDesc(OrderStatus.getStatusCodeDesc(order.getCurrentStatus()));
        orderInfoDto.setOrderNo(order.getOrderNo());
        orderInfoDto.setOrderDate(order.getCreateTime());
        orderInfoDto.setTotal(order.getTotal());
        orderInfoDto.setCustomerName(buyerProfile.getRealName());
        orderInfoDto.setAddress(buyerProfile.getAddress());
        orderInfoDto.setSupplierName(supplierProfile.getRealName());
        return orderInfoDto;
    }

}
