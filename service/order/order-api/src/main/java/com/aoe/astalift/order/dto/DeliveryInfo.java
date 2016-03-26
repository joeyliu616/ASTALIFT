package com.aoe.astalift.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 16-3-22.
 */
public class DeliveryInfo {
    @JsonProperty
    private String company;
    @JsonProperty
    private String deliveryNo;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }
}
