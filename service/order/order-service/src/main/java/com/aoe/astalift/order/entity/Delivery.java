package com.aoe.astalift.order.entity;

import javax.persistence.*;

/**
 * Created by joey on 16-3-22.
 */

@Entity
@Table(name = "t_delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String company;
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
