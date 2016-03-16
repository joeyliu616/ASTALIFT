package com.aoe.astalift.order.service;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.dto.OrderInfoDto;

import java.util.List;

/**
 * Created by joey on 16-3-16.
 */
public interface SellerOrderService {

    //列出所有订单
    BaseResponse<List<OrderInfoDto>> listOrderByCustomer(String customerId);

    //根据状态列出订单
    public BaseResponse<List<OrderInfoDto>> listOrderByCustomerAndStatus(String customerId, Integer status);



}
