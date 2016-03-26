package com.aoe.astalift.order.service.impl;

import com.aoe.astalift.account.constants.AccountError;
import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import com.aoe.astalift.order.constants.OrderError;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.DeliveryInfo;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.repository.OrderRepository;
import com.aoe.astalift.order.service.SupplierOrderProcessService;
import com.aoe.astalift.order.util.OrderDtoUtil;
import com.aoe.astalift.order.util.OrderStatusHistoryUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joey on 16-3-22.
 */
@Service("supplierOrderProcessService")
public class SupplierOrderProcessServiceImpl implements SupplierOrderProcessService {

    @Resource
    OrderRepository orderRepository;

    @Resource
    ProfileService profileService;

    public BaseResponse<List<OrderInfoDto>> listOrder(Integer supplierId) {
        if(null == supplierId){
            return new BaseResponse<List<OrderInfoDto>>(new AccountError.UserNotExist());
        }

        List<Order> bySupplierId = orderRepository.findBySupplierId(supplierId);
        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : bySupplierId) {
            ProfileInfo buyerProfile = profileService.getUserProfile(order.getBuyerId()).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile, supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }

        return new BaseResponse<List<OrderInfoDto>>(orderInfoDtos);
    }

    public BaseResponse<List<OrderInfoDto>> listUnfinishedOrders(Integer supplierId) {
        if(null == supplierId){
            return new BaseResponse<List<OrderInfoDto>>(new AccountError.UserNotExist());
        }
        List<Integer> unfinedStatus = new LinkedList<Integer>();

        unfinedStatus.add(OrderStatus.CANCEL.getCode());
        unfinedStatus.add(OrderStatus.END.getCode());
        unfinedStatus.add(OrderStatus.REFUSE.getCode());

        List<Order> bySupplierId = orderRepository.findBySupplierIdAndCurrentStatusNotIn(supplierId,unfinedStatus);
        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : bySupplierId) {
            ProfileInfo buyerProfile = profileService.getUserProfile(order.getBuyerId()).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile, supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }
        return new BaseResponse<List<OrderInfoDto>>(orderInfoDtos);
    }

    public BaseResponse<List<OrderInfoDto>> listUnAcceptedOrders(Integer supplierId) {
        if(null == supplierId){
            return new BaseResponse<List<OrderInfoDto>>(new AccountError.UserNotExist());
        }
        List<Order> bySupplierIdAndCurrentStatus = orderRepository.findBySupplierIdAndCurrentStatus(supplierId, OrderStatus.INIT.getCode());
        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : bySupplierIdAndCurrentStatus) {
            ProfileInfo buyerProfile = profileService.getUserProfile(order.getBuyerId()).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile, supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }
        return new BaseResponse<List<OrderInfoDto>>(orderInfoDtos);
    }

    public BaseResponse<List<OrderInfoDto>> listFinishedOrders(Integer supplierId) {
        if(null == supplierId){
            return new BaseResponse<List<OrderInfoDto>>(new AccountError.UserNotExist());
        }
        List<Order> bySupplierIdAndCurrentStatus = orderRepository.findBySupplierIdAndCurrentStatus(supplierId, OrderStatus.END.getCode());
        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : bySupplierIdAndCurrentStatus) {
            ProfileInfo buyerProfile = profileService.getUserProfile(order.getBuyerId()).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile, supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }
        return new BaseResponse<List<OrderInfoDto>>(orderInfoDtos);
    }

    public BaseResponse<OrderDetail> getOrderDetail(String orderNo) {
        return null;
    }

    public BaseResponse confirmOrder(String orderNo) {
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderInfoDto>(new OrderError.OrderNotExist());
        }
        if(byOrderNo.getCurrentStatus() != OrderStatus.INIT.getCode()){
            return new BaseResponse<OrderInfoDto>(new OrderError.CannotConfirm());
        }
        OrderStatusHistoryUtil.addOrderStatusHistory(byOrderNo, OrderStatus.CONFIRM, "供应商接受该订单");
        Order save = orderRepository.save(byOrderNo);
        if(null != save){
            return new BaseResponse();
        }
        return new BaseResponse(new CommonErrors.DataBaseError());
    }

    public BaseResponse finishOrder(String orderNo) {
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderInfoDto>(new OrderError.OrderNotExist());
        }
        //TODO
        //if(byOrderNo.getCurrentStatus() != OrderStatus.DELIVERING.getCode()){
        //    return new BaseResponse<OrderInfoDto>(new OrderError.CannotConfirm());
        //}
        OrderStatusHistoryUtil.addOrderStatusHistory(byOrderNo, OrderStatus.END, "买家已付款，订单结束");
        Order save = orderRepository.save(byOrderNo);
        if(null != save){
            return new BaseResponse();
        }
        return new BaseResponse(new CommonErrors.DataBaseError());
    }

    public BaseResponse<OrderInfoDto> cancelOrder(String orderNo, String desc) {
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderInfoDto>(new OrderError.OrderNotExist());
        }
        if(byOrderNo.getCurrentStatus() != OrderStatus.INIT.getCode()){
            return new BaseResponse<OrderInfoDto>(new OrderError.CannotConfirm());
        }
        OrderStatusHistoryUtil.addOrderStatusHistory(byOrderNo, OrderStatus.CANCEL, "供应商接受该订单");
        Order save = orderRepository.save(byOrderNo);
        if(null != save){
            return new BaseResponse();
        }
        return new BaseResponse(new CommonErrors.DataBaseError());
    }

    public BaseResponse<OrderInfoDto> setDeliveryInfo(String orderNo, DeliveryInfo deliveryInfo) {
        return null;
    }
}
