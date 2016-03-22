package com.aoe.astalift.web.controller;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.service.SellerOrderService;
import com.aoe.astalift.web.annotation.SessionValidIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-16.
 */
@RestController
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource(name =  "sellerOrderService")
    SellerOrderService sellerOrderService;

    @RequestMapping(value = "/sell/orders", method = RequestMethod.GET)
    BaseResponse getOrders(){
        logger.debug("hi orders..");
        return sellerOrderService.listOrderByCustomerAndStatus("1",0);
    }
}
