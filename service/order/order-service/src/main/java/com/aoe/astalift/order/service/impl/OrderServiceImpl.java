package com.aoe.astalift.order.service.impl;

import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import com.aoe.astalift.common.dto.ResponseUtil;
import com.aoe.astalift.order.constants.OrderError;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.request.OrderItemDto;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.dto.response.OrderHistoryDto;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.dto.response.OrderItemDetail;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.entity.OrderItem;
import com.aoe.astalift.order.entity.OrderStatusHistory;
import com.aoe.astalift.order.repository.OrderRepository;
import com.aoe.astalift.order.service.OrderService;
import com.aoe.astalift.order.util.OrderDtoUtil;
import com.aoe.astalift.order.util.OrderNoGenerator;
import com.aoe.astalift.order.util.OrderStatusHistoryUtil;
import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by joey on 16-3-27.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    ProfileService profileService;

    @Resource
    OrderRepository orderRepository;

    @Resource
    ProductService productService;


    public BaseResponse<OrderDetail> order(List<OrderItemDto> orderItemDtoList, Integer buyerId, Integer supplierId,String receiveAddress, String mobile) {
        String orderNo =  OrderNoGenerator.generate();

        float sum = 0;
        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setSupplierId(supplierId);

        order.setCurrentStatus(OrderStatus.INIT.getCode());
        order.setReceiveAddress(receiveAddress);
        order.setReceiveContact(mobile);
        order.setOrderNo(orderNo);

        Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
        for (OrderItemDto orderItemDto : orderItemDtoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAmount(orderItemDto.getAmount());
            if(0 == orderItemDto.getAmount()){
                return new BaseResponse<OrderDetail>(new OrderError.ItemAmountError());
            }
            orderItem.setProductId(orderItemDto.getProductId());
            BaseResponse<ProductDetail> productDetailBaseResponse = productService.getProductDetail(orderItem.getProductId());
            if(false == ResponseUtil.isResponseSuccess(productDetailBaseResponse)){
                return new BaseResponse<OrderDetail>(ResponseUtil.getErrorTemplate(productDetailBaseResponse));
            }
            orderItem.setPrice(productDetailBaseResponse.getData().getPrice());
            orderItem.setDiscount(0.8f);
            orderItemSet.add(orderItem);
            sum += (orderItem.getAmount() * orderItem.getPrice() * orderItem.getDiscount());
        }
        order.setOrderItems(orderItemSet);
        order.setTotal(sum);
        OrderStatusHistoryUtil.addOrderStatusHistory(order, OrderStatus.INIT, "用户下单");

        Order save = orderRepository.save(order);
        if(null == save){
            return new BaseResponse<OrderDetail>(new CommonErrors.DataBaseError());
        }
        OrderDetail detail = new OrderDetail();
        detail.setOrderNo(orderNo);

        //TODO set orderDetail
        return new BaseResponse<OrderDetail>(detail);
    }

    public BaseResponse<OrderDetail> setOrderStatus(String orderNo, OrderStatus status, String msg) {
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderDetail>(new OrderError.OrderNotExist());
        }
        if(checkStatus(byOrderNo.getCurrentStatus(), status.getCode())){
            Order order = OrderStatusHistoryUtil.addOrderStatusHistory(byOrderNo, status, msg);
            Order save = orderRepository.save(order);
            if(null == save){
                return new BaseResponse<OrderDetail>(new CommonErrors.DataBaseError());
            }
        }else{
            return new BaseResponse<OrderDetail>(new OrderError.StatusError());
        }
        return new BaseResponse<OrderDetail>();
    }

    public BaseResponse<OrderDetail> getOrderDetail(String orderNo) {

        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderDetail>(new OrderError.OrderNotExist());
        }

        BaseResponse<ProfileInfo> buyerProfileResponse = profileService.getUserProfile(byOrderNo.getBuyerId());
        BaseResponse<ProfileInfo> supplierProfileResponse = profileService.getUserProfile(byOrderNo.getSupplierId());
        OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(byOrderNo, buyerProfileResponse.getData(), supplierProfileResponse.getData());
        OrderDetail orderDetail = new OrderDetail(orderInfoDto);

        Set<OrderStatusHistory> orderStatusHistories = byOrderNo.getOrderStatusHistories();
        List<OrderHistoryDto> histories = new LinkedList<OrderHistoryDto>();

        Set<OrderItem> orderItems = byOrderNo.getOrderItems();
        List<OrderItemDetail> orderItemDetailList = new LinkedList<OrderItemDetail>();

        for (OrderStatusHistory orderStatusHistory : orderStatusHistories) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
            orderHistoryDto.setDesc(orderStatusHistory.getDesc());
            orderHistoryDto.setCreateTime(orderStatusHistory.getCreateTime());
            orderHistoryDto.setStatus(orderStatusHistory.getStatus());
            histories.add(orderHistoryDto);
        }

        for (OrderItem orderItem : orderItems) {
            OrderItemDetail orderItemDetail = new OrderItemDetail();
            Integer productId = orderItem.getProductId();
            ProductDetail productDetail = productService.getProductDetail(productId).getData();
            orderItemDetail.setProductTitle(productDetail.getTitle());
            orderItemDetail.setProductTitleImage(productDetail.getTitleImage());
            orderItemDetail.setAmount(orderItem.getAmount());
            orderItemDetail.setDiscount(orderItem.getDiscount());
            orderItemDetail.setOriginalPrice(productDetail.getPrice());
            orderItemDetail.setProductId(productDetail.getId());
            orderItemDetailList.add(orderItemDetail);
        }

        orderDetail.setHistories(histories);
        orderDetail.setOrderItemDetailList(orderItemDetailList);
        return new BaseResponse<OrderDetail>(orderDetail);
    }

    private boolean checkStatus(Integer currentStatus, Integer newStatus){
        //mark a order to error.
        if(newStatus == OrderStatus.ERROR.getCode()){
            return true;
        }

        //INIT
        if(OrderStatus.INIT.getCode() == currentStatus){
            if(OrderStatus.CANCEL.getCode() == newStatus
                    || OrderStatus.CONFIRM.getCode() == newStatus
                    || OrderStatus.REFUSE.getCode() == newStatus){
                return true;
            }
            return false;
        }

        //confirm
        if(OrderStatus.CONFIRM.getCode() == currentStatus){
            if(newStatus == OrderStatus.PAID.getCode()){
                return true;
            }
            return false;
        }

        //pay
        if(OrderStatus.PAID.getCode() == currentStatus){
            if(newStatus == OrderStatus.DELIVERING.getCode()){
                return true;
            }
            return false;
        }

        //deliver
        if(OrderStatus.DELIVERING.getCode() == currentStatus){
            if(newStatus == OrderStatus.RECEIVED.getCode()
                    || newStatus == OrderStatus.TIMEOUT.getCode()){
                return true;
            }
            return false;
        }

        //RECEIVED
        if(OrderStatus.RECEIVED.getCode() == currentStatus){
            if(newStatus == OrderStatus.END.getCode()){
                return true;
            }
            return false;
        }

        //TIMEOUT
        if(OrderStatus.TIMEOUT.getCode() == currentStatus){
            if(newStatus == OrderStatus.END.getCode()){
                return true;
            }
            return false;
        }

        if(OrderStatus.END.getCode() == currentStatus){
            return false;
        }

        if(OrderStatus.CANCEL.getCode() == currentStatus){
            return false;
        }

        if(OrderStatus.REFUSE.getCode() == currentStatus){
            return false;
        }

        return false;
    }

}