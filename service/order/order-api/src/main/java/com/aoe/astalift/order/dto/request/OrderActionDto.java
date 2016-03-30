package com.aoe.astalift.order.dto.request;

import com.aoe.astalift.order.dto.DeliveryInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 16-3-24.
 */
public class OrderActionDto {

    @JsonProperty
    private String actionType;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @JsonProperty
    private DeliveryInfo extra;

    public DeliveryInfo getDeliveryInfo() {
        return extra;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.extra = deliveryInfo;
    }
}
