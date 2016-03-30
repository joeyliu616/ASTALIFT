package com.aoe.astalift.order.monitor;

import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.repository.OrderRepository;
import com.aoe.astalift.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joey on 16-3-30.
 */
@Service
@EnableScheduling
public class OrderStatusMonitor {

    private static Logger logger = LoggerFactory.getLogger(OrderStatusMonitor.class);

    @Resource
    OrderService orderService;

    @Resource
    OrderRepository orderRepository;

   // @Scheduled(fixedRate=5000)
    public void autoFinishJob(){
        logger.debug("定时任务： autoFinishJob ");
        List<Integer> autoFinishStatus = new ArrayList<Integer>();
        autoFinishStatus.add(OrderStatus.REFUSE.getCode());
        autoFinishStatus.add(OrderStatus.TIMEOUT.getCode());
        autoFinishStatus.add(OrderStatus.RECEIVED.getCode());

        List<Order> orderList = orderRepository.findByCurrentStatusIn(autoFinishStatus);
        for (Order order : orderList) {
            logger.info("orderNo {}, currentStatus {}, auto finish", order.getOrderNo(), OrderStatus.getStatusCodeDesc(order.getCurrentStatus()));
            orderService.setOrderStatus(order.getOrderNo(),OrderStatus.END,"系统发现 {}"+order.getCurrentStatus() +" 订单, 自动关闭");
        }
    }
}
