package com.aoe.astalift.product.test;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.product.ProductServiceConfigHook;
import com.aoe.astalift.product.dto.ProductInfo;
import com.aoe.astalift.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by joey on 16-3-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProductServiceConfigHook.class)
public class TestListProduct {

    @Resource
    ProductService productService;

    @Resource
    ObjectMapper objectMapper;

    @Test
    public void foo() throws JsonProcessingException {
        Assert.notNull(productService);
        BaseResponse<List<ProductInfo>> baseResponse = productService.listProduct();
        System.out.println(objectMapper.writeValueAsString(baseResponse));
    }
}
