package com.aoe.astalift.order.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by joey on 16-3-16.
 */
@Entity
@Table(name="t_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //订单号
    @Column(nullable = false, unique = true)
    private String orderNo;

    //供应方id
    @Column(nullable = false)
    private Integer supplierId;

    //供应方名称
    @Column
    private String supplierName;

    //采购方id
    @Column(nullable = false)
    private Integer buyerId;

    //收货地址
    @Column(nullable = false)
    private String receiveAddress;

    //收货人电话
    @Column(nullable = false)
    private String receiveContact;

    //订单总额 根据OrderItems中的总和得出.
    @Column(nullable = false)
    private Long total;

    //状态
    @Column(nullable = false)
    private Integer currentStatus;

    @OneToMany
    @JoinColumn(name="orderNo")
    private Set<OrderStatusHistory> orderStatusHistories;

    //订单详情. 采购了哪些产品，单价多少， 折扣多少, 总价多少
    @OneToMany
    @JoinColumn(name="orderNo")
    private Set<OrderItem> orderItems;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    @OneToOne
    private Delivery delivery;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveContact() {
        return receiveContact;
    }

    public void setReceiveContact(String receiveContact) {
        this.receiveContact = receiveContact;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Set<OrderStatusHistory> getOrderStatusHistories() {
        return orderStatusHistories;
    }

    public void setOrderStatusHistories(Set<OrderStatusHistory> orderStatusHistories) {
        this.orderStatusHistories = orderStatusHistories;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
