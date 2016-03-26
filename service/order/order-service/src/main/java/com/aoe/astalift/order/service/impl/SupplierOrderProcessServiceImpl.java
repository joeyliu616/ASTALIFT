package com.aoe.astalift.order.service.impl;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.dto.dto.DeliveryInfo;
import com.aoe.astalift.dto.dto.OrderDetail;
import com.aoe.astalift.dto.dto.OrderInfoDto;
import com.aoe.astalift.order.service.SupplierOrderProcessService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
@Service("supplierOrderProcessService")
public class SupplierOrderProcessServiceImpl implements SupplierOrderProcessService {
    public BaseResponse<List<OrderInfoDto>> listOrderBySupplier(Integer supplierId) {
        return null;
    }

    public BaseResponse<List<OrderInfoDto>> listOrderByBuyer(Integer buyerId) {
        return null;
    }

    public BaseResponse<OrderDetail> getOrderDetail(String orderNo) {
        return null;
    }

    public BaseResponse<OrderInfoDto> confirmOrder(String orderNo) {
        return null;
    }

    public BaseResponse<OrderInfoDto> cancelOrder(String orderNo, String desc) {
        return null;
    }

    public BaseResponse<OrderInfoDto> setDeliveryInfo(String orderNo, DeliveryInfo deliveryInfo) {
        return null;
    }
}
