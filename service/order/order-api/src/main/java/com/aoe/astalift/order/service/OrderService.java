package com.aoe.astalift.order.service;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.request.OrderItemDto;
import com.aoe.astalift.order.dto.response.OrderDetail;

import java.util.List;

/**
 * Created by joey on 16-3-27.
 */
public interface OrderService {

    BaseResponse<OrderDetail> order( List<OrderItemDto> orderItemDtoList,Integer buyerId, Integer supplierId, String receiveAddress, String mobile);

    BaseResponse<OrderDetail> setOrderStatus(String orderNo, OrderStatus status,String msg);

    BaseResponse<OrderDetail> getOrderDetail(String orderNo);

}