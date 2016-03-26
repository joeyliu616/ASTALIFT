package com.aoe.astalift.order.service;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.dto.dto.DeliveryInfo;
import com.aoe.astalift.dto.dto.OrderDetail;
import com.aoe.astalift.dto.dto.OrderInfoDto;

import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
public interface SupplierOrderProcessService {

    BaseResponse<List<OrderInfoDto>> listOrderBySupplier(Integer supplierId);

    BaseResponse<List<OrderInfoDto>> listOrderByBuyer(Integer buyerId);

    BaseResponse<OrderDetail> getOrderDetail(String orderNo);

    BaseResponse<OrderInfoDto> confirmOrder(String orderNo);

    BaseResponse<OrderInfoDto> cancelOrder(String orderNo, String desc);

    BaseResponse<OrderInfoDto> setDeliveryInfo(String orderNo, DeliveryInfo deliveryInfo);
}
