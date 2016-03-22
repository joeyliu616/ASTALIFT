package com.aoe.astalift.product.dto.util;

import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.dto.ProductInfo;
import com.aoe.astalift.product.entity.Product;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joey on 16-3-17.
 */
public class ProductDtoUtil {

    public static ProductInfo createProductInfo(Product product){
        ProductInfo info = new ProductInfo();
        info.setId(product.getId());
        info.setTitle(product.getTitle());
        info.setPrice(product.getPrice());
        info.setTitleImage(product.getTitleImage().getUrl());
        info.setType(product.getType());
        return info;
    }

    public static List<ProductInfo> createProductInfoList(List<Product> products){
        List<ProductInfo> productInfoList = new LinkedList<ProductInfo>();
        for (Product product : products) {
            productInfoList.add(createProductInfo(product));
        }
        return productInfoList;
    }

    public static ProductDetail createProductDetail(Product product) {
        ProductDetail productDetail = new ProductDetail(createProductInfo(product));
        //TODO add more
        return productDetail;
    }
}
