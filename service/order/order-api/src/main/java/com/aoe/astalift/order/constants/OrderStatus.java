package com.aoe.astalift.order.constants;

/**
 * Created by joey on 16-3-22.
 */
public enum OrderStatus {

    INIT(1,"买家下单"), CONFIRM(2,"商家确认"), STOCKING(3,"商家备货中"),DELIVERING(4,"物流过程中"),RECEIVED(5,"已收货"),END(100,"订单完成");

    private String desc;
    private Integer code;

    OrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
