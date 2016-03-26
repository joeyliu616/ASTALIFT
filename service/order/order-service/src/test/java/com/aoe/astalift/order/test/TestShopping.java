package com.aoe.astalift.order.test;



import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.dto.request.OrderItemDto;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.repository.OrderRepository;
import com.aoe.astalift.order.service.BuyerShoppingService;
import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by joey on 16-3-22.
 */
public class TestShopping extends TestTemplate {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    BuyerShoppingService buyerShoppingService;

    @Resource
    ProductService productService;

    @Resource
    AccountService accountService;

    @Resource
    ProfileService profileService;

    @Resource
    OrderRepository orderRepository;

    @Test
    public void foo() throws JsonProcessingException {

        Assert.notNull(buyerShoppingService);
        Assert.notNull(productService);
        Assert.notNull(accountService);
        Assert.notNull(productService);

        ProfileInfo profileInfo = profileService.getUserProfile(3).getData();
        String address = profileInfo.getAddress();
        String mobile = profileInfo.getMobile();

        List<OrderItemDto> orderItemDtos = randomOrderItemDtos();
        BaseResponse<OrderDetail> orderProductsResponse = buyerShoppingService.orderProducts(
                orderItemDtos, 3, 2, address, mobile);

        Assert.notNull(orderProductsResponse);
        Assert.isTrue(orderProductsResponse.getCode() == 0);
        Assert.isTrue(orderRepository.findByBuyerId(3) != null);
        Assert.isTrue(orderRepository.findBySupplierId(2) != null);

        String orderNo = orderProductsResponse.getData().getOrderNo();
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);

        Assert.notNull(byOrderNo);
        Assert.isTrue(byOrderNo.getOrderNo().equals(orderNo));
        Assert.isTrue(byOrderNo.getCurrentStatus() == OrderStatus.INIT.getCode());


        BaseResponse<OrderDetail> response = buyerShoppingService.cancelOrder(orderNo, "点错啦");
        Assert.notNull(response);
        Assert.notNull(response.getData());


        BaseResponse<List<OrderInfoDto>> listBaseResponse = buyerShoppingService.listOrder(3);
        Assert.isTrue(listBaseResponse.getCode() == 0);
        Assert.isTrue(listBaseResponse.getData().size() != 0);

        System.out.println(objectMapper.writeValueAsString(listBaseResponse));

        BaseResponse<OrderDetail> orderDetail = buyerShoppingService.getOrderDetail(orderNo);
        Assert.isTrue(orderDetail.getCode() == 0);
        System.out.println(objectMapper.writeValueAsString(orderDetail));

    }



    private List<OrderItemDto> randomOrderItemDtos(){
        List<OrderItemDto> orderItemDtos = new LinkedList<OrderItemDto>();

        for (int i = 1; i < 4; i++) {
            BaseResponse<ProductDetail> response = productService.getProductDetail(i);
            Assert.notNull(response);
            Assert.isTrue(response.getData() != null);
            ProductDetail productDetail = response.getData();
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProductId(productDetail.getId());
            orderItemDto.setOrderPrice(productDetail.getPrice() * 0.8f);
            Random random = new Random();
            orderItemDto.setAmount(1 + random.nextInt(10));
            orderItemDtos.add(orderItemDto);
        }

        return orderItemDtos;

    }
}

