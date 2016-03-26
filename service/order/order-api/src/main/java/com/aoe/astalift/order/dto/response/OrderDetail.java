package com.aoe.astalift.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
public class OrderDetail extends OrderInfoDto implements Serializable{

    @JsonProperty
    private List<OrderItemDetail> orderItemDetailList;

    @JsonProperty
    private List<OrderHistoryDto> histories;


    public List<OrderItemDetail> getOrderItemDetailList() {
        return orderItemDetailList;
    }

    public void setOrderItemDetailList(List<OrderItemDetail> orderItemDetailList) {
        this.orderItemDetailList = orderItemDetailList;
    }

    public List<OrderHistoryDto> getHistories() {
        return histories;
    }

    public void setHistories(List<OrderHistoryDto> histories) {
        this.histories = histories;
    }
}
