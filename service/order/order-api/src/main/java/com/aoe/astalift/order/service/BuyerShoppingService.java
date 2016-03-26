package com.aoe.astalift.order.service;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.dto.dto.OrderDetail;
import com.aoe.astalift.dto.dto.OrderInfoDto;
import com.aoe.astalift.dto.dto.OrderItemDto;

import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
public interface BuyerShoppingService {

    public BaseResponse<OrderInfoDto> orderProducts(List<OrderItemDto> orderItemDtos,
                                                    Float total, Integer buyerId, Integer supplierId,
                                                    String receiveAddress, String mobile);

    BaseResponse<OrderInfoDto> confirmDelivered(String orderNo);

    BaseResponse<OrderInfoDto> cancelOrder(String orderNo, String msg);

    BaseResponse<OrderDetail> getOrderDetail(String orderNo);

    BaseResponse<List<OrderInfoDto>> listOrder(Integer buyerId);


}
