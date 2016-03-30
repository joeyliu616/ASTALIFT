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

    public OrderDetail() {
        super();
    }

    public OrderDetail(OrderInfoDto orderInfoDto) {
        super();
        this.setStatusDesc(orderInfoDto.getStatusDesc());
        this.setSupplierName(orderInfoDto.getSupplierName());
        this.setOrderDate(orderInfoDto.getOrderDate());
        this.setStatusDesc(orderInfoDto.getStatusDesc());
        this.setStatusCode(orderInfoDto.getStatusCode());
        this.setCustomerName(orderInfoDto.getCustomerName());
        this.setAddress(orderInfoDto.getAddress());
        this.setOrderNo(orderInfoDto.getOrderNo());
        this.setTotal(orderInfoDto.getTotal());
        this.setMobile(orderInfoDto.getMobile());
        this.setSupplierMobile(orderInfoDto.getSupplierMobile());
        this.setSupplierAddress(orderInfoDto.getSupplierAddress());
    }


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
