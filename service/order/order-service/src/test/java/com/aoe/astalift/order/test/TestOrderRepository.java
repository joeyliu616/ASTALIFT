package com.aoe.astalift.order.test;

import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.repository.OrderRepository;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joey on 16-3-24.
 */
public class TestOrderRepository extends TestTemplate{

    @Resource
    OrderRepository orderRepository;

    @Test
    public void foo(){

        List<Integer> fooList = new ArrayList<Integer>();
        fooList.add(-1);
        List<Order> byBuyerIdAndCurrentStatusNotIn = orderRepository.findByBuyerIdAndCurrentStatusNotIn(3, fooList);

        for (Order order : byBuyerIdAndCurrentStatusNotIn) {
            System.out.println(order.getId() + "  " + order.getBuyerId());
        }
    }
}
