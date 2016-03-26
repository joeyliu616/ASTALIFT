package com.aoe.astalift.order.service.impl;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.dto.dto.OrderDetail;
import com.aoe.astalift.dto.dto.OrderInfoDto;
import com.aoe.astalift.dto.dto.OrderItemDto;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.repository.OrderRepository;
import com.aoe.astalift.order.service.BuyerShoppingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by joey on 16-3-22.
 */
@Service("buyerShoppingService")
public class BuyerShoppingServiceImpl implements BuyerShoppingService {

    @Resource
    OrderRepository orderRepository;

    public BaseResponse<OrderInfoDto> orderProducts(List<OrderItemDto> orderItemDtos, Float total, Integer buyerId, Integer supplierId, String receiveAddress, String mobile) {
        Order order = new Order();

        order.setBuyerId(buyerId);
        order.setSupplierId(supplierId);

        order.setCurrentStatus(OrderStatus.INIT.getCode());
        order.setReceiveAddress(receiveAddress);
        order.setReceiveContact(mobile);
        order.setOrderNo(UUID.randomUUID().toString().replaceAll("-",""));

        for (OrderItemDto orderItemDto : orderItemDtos) {

        }
        return null;
    }

    public BaseResponse<OrderInfoDto> confirmDelivered(String orderNo) {
        return null;
    }

    public BaseResponse<OrderInfoDto> cancelOrder(String orderNo, String msg) {
        return null;
    }

    public BaseResponse<OrderDetail> getOrderDetail(String orderNo) {
        return null;
    }

    public BaseResponse<List<OrderInfoDto>> listOrder(Integer buyerId) {
        return null;
    }
}
