package com.aoe.astalift.order.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joey on 16-3-22.
 */
public enum OrderStatus {

    CANCEL(-1,"客户取消"),
    REFUSE(-2,"商家拒绝"),
    ERROR(-100, "订单异常"),

    INIT(1,"客户下单"),
    CONFIRM(2,"备货完成,等待买家付款"),
    PAID(3,"确认买家已经付款"),
    DELIVERING(4,"物流过程中"),
    RECEIVED(5,"已收货"),
    TIMEOUT(6,"物流超期,自动结束")
    ,END(100,"订单完成");

    private String desc;
    private Integer code;
    private static Map<Integer ,String> descMap = new HashMap<Integer ,String>();
    private static Map<String, Integer> reversDescMap = new HashMap<String, Integer>();

    static {

        descMap.put(CANCEL.getCode(),CANCEL.getDesc());
        descMap.put(REFUSE.getCode(),REFUSE.getDesc());
        descMap.put(ERROR.getCode(),ERROR.getDesc());
        descMap.put(INIT.getCode(),INIT.getDesc());
        descMap.put(CONFIRM.getCode(),CONFIRM.getDesc());
        descMap.put(PAID.getCode(),PAID.getDesc());
        descMap.put(DELIVERING.getCode(),DELIVERING.getDesc());
        descMap.put(RECEIVED.getCode(),RECEIVED.getDesc());
        descMap.put(TIMEOUT.getCode(),TIMEOUT.getDesc());
        descMap.put(END.getCode(),END.getDesc());

        reversDescMap.put(CANCEL.getDesc(),CANCEL.getCode());
        reversDescMap.put(REFUSE.getDesc(),REFUSE.getCode());
        reversDescMap.put(ERROR.getDesc(),ERROR.getCode());
        reversDescMap.put(INIT.getDesc(),INIT.getCode());
        reversDescMap.put(CONFIRM.getDesc(),CONFIRM.getCode());
        reversDescMap.put(PAID.getDesc(),PAID.getCode());
        reversDescMap.put(DELIVERING.getDesc(),DELIVERING.getCode());
        reversDescMap.put(RECEIVED.getDesc(),RECEIVED.getCode());
        reversDescMap.put(TIMEOUT.getDesc(),TIMEOUT.getCode());
        reversDescMap.put(END.getDesc(),END.getCode());

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
        return descMap.get(code);
    }

    public static Integer getStatusCode(String desc){
        return reversDescMap.get(desc);
    }

}
