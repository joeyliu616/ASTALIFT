package com.aoe.astalift.order.service;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.dto.DeliveryInfo;
import com.aoe.astalift.order.dto.request.OrderActionDto;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.dto.response.OrderInfoDto;

import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
public interface SupplierOrderProcessService {

    BaseResponse<List<OrderInfoDto>> listOrder(Integer supplierId);

    BaseResponse<List<OrderInfoDto>> listUnfinishedOrders(Integer supplierId);

    BaseResponse<List<OrderInfoDto>> listUnAcceptedOrders(Integer supplierId);

    BaseResponse<List<OrderInfoDto>> listFinishedOrders(Integer supplierId);

    BaseResponse<OrderDetail> getOrderDetail(String orderNo);

    BaseResponse confirmOrder(String orderNo);

    BaseResponse finishOrder(String orderNo);

    BaseResponse<OrderInfoDto> cancelOrder(String orderNo, String desc);

    BaseResponse<OrderInfoDto> setDeliveryInfo(String orderNo, DeliveryInfo deliveryInfo);



}
