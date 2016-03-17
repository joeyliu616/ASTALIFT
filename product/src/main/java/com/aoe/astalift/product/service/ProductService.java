package com.aoe.astalift.product.service;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.dto.ProductInfo;

import java.util.List;

/**
 * Created by joey on 16-3-16.
 */
public interface ProductService {

    //产品列表
    BaseResponse<List<ProductInfo>> listProduct();

    //根据类型查询产品列表
    BaseResponse<List<ProductInfo>> listProductByType(String type);

    //获取产品详情
    BaseResponse<ProductDetail> getProductDetail(String productId);


}
