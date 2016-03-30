package com.aoe.astalift.order.service.impl;

import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.util.OrderDtoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joey on 16-3-29.
 */
@Service("orderServiceUtil")
public class OrderServiceUtil {

    @Resource
    ProfileService profileService;

    public List<OrderInfoDto> getOrderInfoDtos(List<Order> orders){
        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : orders) {
            ProfileInfo buyerProfile = profileService.getUserProfile(order.getBuyerId()).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile, supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }
        return orderInfoDtos;
    }

}
