package com.aoe.astalift.order.service;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.dto.request.OrderItemDto;

import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
public interface BuyerShoppingService {

    BaseResponse<OrderDetail> orderProducts(List<OrderItemDto> orderItemDtos,
                                                    Integer buyerId, Integer supplierId,
                                                    String receiveAddress, String mobile);

    BaseResponse<OrderDetail> confirmDelivered(String orderNo);

    BaseResponse<OrderDetail> cancelOrder(String orderNo, String msg);

    BaseResponse<OrderDetail> getOrderDetail(String orderNo);

    BaseResponse<List<OrderInfoDto>> listOrder(Integer buyerId);

    BaseResponse<List<OrderInfoDto>> listUnfinishedOrder(Integer buyerId);

    BaseResponse<List<OrderInfoDto>> listFinishedOrders(Integer buyerId);
}
