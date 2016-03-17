package com.aoe.astalift.web.controller;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.product.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by joey on 16-3-17.
 */
@RestController
public class ProductController {

    @Resource(name="productService")
    ProductService productService;


    @RequestMapping("/products")
    public BaseResponse productList(){
        return productService.listProduct();
    }
}
