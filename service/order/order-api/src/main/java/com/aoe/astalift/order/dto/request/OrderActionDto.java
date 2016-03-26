package com.aoe.astalift.order.dto.request;

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
}
