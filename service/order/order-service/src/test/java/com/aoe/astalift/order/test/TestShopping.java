package com.aoe.astalift.order.test;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.dto.dto.OrderInfoDto;
import com.aoe.astalift.dto.dto.OrderItemDto;
import com.aoe.astalift.order.service.service.BuyerShoppingService;
import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.service.ProductService;
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
    BuyerShoppingService buyerShoppingService;

    @Resource
    ProductService productService;

    @Test
    public void foo(){
        Assert.notNull(buyerShoppingService);
        Assert.notNull(productService);

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
            orderItemDto.setAmount(random.nextInt(10));
            orderItemDtos.add(orderItemDto);
        }
        BaseResponse<OrderInfoDto> orderProductsResponse = buyerShoppingService.orderProducts(orderItemDtos, 3, 2);
        Assert.notNull(orderProductsResponse);
        Assert.isTrue(orderProductsResponse.getCode() == 0);
        Assert.isTrue(orderProductsResponse.getData() != null);


        //List<OrderItemDto> orderItemDtos = new LinkedList<OrderItemDto>();
        //for (int i = 1; i < 4; i++) {
        //    OrderItemDto dto = new OrderItemDto();
        //    dto.setAmount(10);
        //    dto.setOrderPrice(680f);
        //    dto.setProductId(i);
        //    orderItemDtos.add(dto);
        //}
        //buyerShoppingService.orderProducts(orderItemDtos,3,2);
    }
}
