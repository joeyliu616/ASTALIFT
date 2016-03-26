package com.aoe.astalift.order.repository;

import com.aoe.astalift.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
    Order findByOrderNo(String orderNo);
    List<Order> findBySupplierId(Integer supperId);
    List<Order> findByBuyerId(Integer buyerId);
    List<Order> findBySupplierIdAndCurrentStatus(Integer supperId, Integer currentStatus);
    List<Order> findBySupplierIdAndCurrentStatusNotIn(Integer supperId, Collection<Integer> statusCollection);
    List<Order> findByBuyerIdAndCurrentStatusNotIn(Integer buyerId, Collection<Integer> statusCollection);
    List<Order> findByBuyerIdAndCurrentStatus(Integer buyerId, Integer currentStatus);
}
