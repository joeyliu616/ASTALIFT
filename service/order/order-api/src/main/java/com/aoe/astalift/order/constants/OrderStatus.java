package com.aoe.astalift.order.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joey on 16-3-22.
 */
public enum OrderStatus {

    CANCEL(-1,"买方取消"), REFUSE(-2,"卖方拒绝"),

    INIT(1,"买家下单"), CONFIRM(2,"商家确认,正在备货中"), DELIVERING(3,"物流过程中"),RECEIVED(4,"已收货")

    ,END(100,"订单完成");


    private String desc;
    private Integer code;
    private static Map<Integer ,String> descMap = new HashMap<Integer ,String>();

    static {
        descMap.put(-1, "买方取消");
        descMap.put(-2, "卖方拒绝");
        descMap.put(1,"买家下单");
        descMap.put(2,"商家确认,正在备货中");
        descMap.put(3, "物流过程中");
        descMap.put(4,"已收货");
        descMap.put(100,"订单已完成");
    }

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

    public static String getStatusCodeDesc(Integer code){
        return OrderStatus.descMap.get(code);
    }
}
