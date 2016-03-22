package com.aoe.astalift.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 16-3-18.
 */
public class CartDto {
    @JsonProperty
    private Integer productId;
    @JsonProperty
    private Integer num;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
