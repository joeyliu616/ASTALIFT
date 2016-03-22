package com.aoe.astalift.cart.entity;

import com.aoe.astalift.product.dto.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 16-3-18.
 */
public class CartItem {

    @JsonProperty
    private ProductInfo productInfo;

    @JsonProperty
    private Integer quantity;


    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
