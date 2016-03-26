package com.aoe.astalift.order.repository;

import com.aoe.astalift.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
    List<Order> findByOrderNo(String orderNo);
    List<Order> findBySupplierId(Integer supperId);
    List<Order> findByBuyerId(Integer buyerId);
    List<Order> findBySupplierIdAndCurrentStatus(Integer supperId, Integer currentStatus);
    List<Order> findByBuyerIdAndCurrentStatus(Integer buyerId, Integer currentStatus);
}
