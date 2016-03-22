package com.aoe.astalift.product.dto;

/**
 * Created by joey on 16-3-16.
 */
public class ProductDetail extends ProductInfo {
    //TODO add more
    public ProductDetail(ProductInfo productInfo) {
        this.setId(productInfo.getId());
        this.setTitle(productInfo.getTitle());
        this.setPrice(productInfo.getPrice());
        this.setTitleImage(productInfo.getTitleImage());
        this.setType(productInfo.getType());
    }
}
