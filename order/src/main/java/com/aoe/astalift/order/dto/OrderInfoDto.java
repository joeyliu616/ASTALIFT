package com.aoe.astalift.order.dto;

import com.aoe.astalift.order.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by joey on 16-3-16.
 */
public class OrderInfoDto implements Serializable {

    @JsonProperty(value="order_date")
    private Date orderDate;

    @JsonProperty("total")
    private float total;

    //眼霜-150ml 199x3x85% 每条格式如下： productTitle price x amount x discount
    @JsonProperty("order_content")
    private List<String> orderContent;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("deliver_address")
    private String deliverAddress;

    @JsonProperty("complete_time")
    private Date completeTime;

    @JsonProperty
    private Integer status;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<String> getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(List<String> orderContent) {
        this.orderContent = orderContent;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
