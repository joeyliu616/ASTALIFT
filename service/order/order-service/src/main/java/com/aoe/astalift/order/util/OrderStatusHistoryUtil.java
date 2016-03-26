package com.aoe.astalift.order.util;

import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.entity.OrderStatusHistory;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joey on 16-3-23.
 */
public class OrderStatusHistoryUtil {

    public static Order addOrderStatusHistory(Order order, OrderStatus orderStatus, String msg){

        if(null == order || null == orderStatus || null == msg){
            return null;
        }

        if(null == order.getOrderStatusHistories()){
            Set<OrderStatusHistory> orderStatusHistoryTreeSet = new HashSet<OrderStatusHistory>();
            order.setOrderStatusHistories(orderStatusHistoryTreeSet);
        }

        OrderStatusHistory history = new OrderStatusHistory();
        history.setCreateTime(new Date());
        history.setDesc(msg);
        history.setStatus(orderStatus.getCode());
        order.getOrderStatusHistories().add(history);
        order.setCurrentStatus(history.getStatus());
        return order;

    }
}
