package com.aoe.astalift.order.dto.response;

import com.aoe.astalift.order.dto.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joey on 16-3-16.
 */
public class OrderInfoDto implements Serializable {

    //订单号
    @JsonProperty
    private String orderNo;

    //下单时间
    @JsonProperty(value="orderDate")
    private Date orderDate;

    //订单总金额
    @JsonProperty("total")
    private float total;

    //买方id
    @JsonProperty("customer")
    private String customerName;

    //当前状态
    @JsonProperty
    private Integer status;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOrderDate() {
        return orderDate;
    }


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
