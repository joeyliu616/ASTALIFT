package com.aoe.astalift.product.service.impl;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.product.constants.ProductError;
import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.dto.ProductInfo;
import com.aoe.astalift.product.dto.util.ProductDtoUtil;
import com.aoe.astalift.product.entity.Product;
import com.aoe.astalift.product.repository.ProductRepository;
import com.aoe.astalift.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by joey on 16-3-16.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductRepository productRepository;

    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public BaseResponse<List<ProductInfo>> listProduct() {
        List<Product> products = productRepository.findAll();
        logger.debug("size: products {} ", products.size());
        if(null == products || 0 == products.size()){
            return new BaseResponse<List<ProductInfo>>(ProductError.ProductNotFound.code,ProductError.ProductNotFound.msg );
        }
        return new BaseResponse<List<ProductInfo>>(ProductDtoUtil.createProductInfoList(products));
    }

    public BaseResponse<List<ProductInfo>> listProductByType(String type) {
        List<Product> products = productRepository.findByType(type);
        if(null == products || 0 == products.size()){
            return new BaseResponse<List<ProductInfo>>(ProductError.ProductNotFound.code,ProductError.ProductNotFound.msg );
        }
        return new BaseResponse<List<ProductInfo>>(ProductDtoUtil.createProductInfoList(products));
    }

    public BaseResponse<ProductDetail> getProductDetail(Integer productId) {
        Product product = productRepository.findOne(productId);
        if(null == product ){
            return new BaseResponse<ProductDetail>(ProductError.ProductNotFound.code,ProductError.ProductNotFound.msg );
        }
        return new BaseResponse<ProductDetail>(ProductDtoUtil.createProductDetail(product));
    }
}
