package com.aoe.astalift.order.test;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-27.
 */
public class TestOrderService extends TestTemplate {

    @Resource
    OrderService orderService;

    @Resource
    ObjectMapper objectMapper;

    @Test
    public void foo() throws JsonProcessingException {
        Assert.notNull(orderService);

        String orderNo = "1603270518-01";

       //BaseResponse<OrderDetail> response = orderService.setOrderStatus(orderNo, OrderStatus.CONFIRM, "卖家确认");

       //Assert.isTrue(response.getCode() == 0);

       //Assert.isTrue(orderService.setOrderStatus(orderNo, OrderStatus.CONFIRM, "").getCode() != 0);

       //BaseResponse<OrderDetail> response1 = orderService.setOrderStatus(orderNo, OrderStatus.PAY, "买家已发货");
       //Assert.isTrue(response1.getCode() == 0);

        BaseResponse<OrderDetail> orderDetail = orderService.getOrderDetail(orderNo);
        Assert.isTrue(orderDetail.getCode() == 0);
        System.out.println(objectMapper.writeValueAsString(orderDetail));
    }

}
