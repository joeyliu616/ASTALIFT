package com.aoe.astalift.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by joey on 16-3-23.
 */
public class OrderItemDetail implements Serializable {

    @JsonProperty
    private Integer productId;

    @JsonProperty
    private String productTitle;

    @JsonProperty
    private String productTitleImage;

    @JsonProperty
    private float originalPrice;

    @JsonProperty
    private Integer amount;

    @JsonProperty
    private float discount;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductTitleImage() {
        return productTitleImage;
    }

    public void setProductTitleImage(String productTitleImage) {
        this.productTitleImage = productTitleImage;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
