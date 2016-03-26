package com.aoe.astalift.order.service.impl;

import com.aoe.astalift.account.constants.AccountError;
import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import com.aoe.astalift.common.dto.ResponseUtil;
import com.aoe.astalift.order.constants.OrderError;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.dto.response.OrderHistoryDto;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.dto.request.OrderItemDto;
import com.aoe.astalift.order.dto.response.OrderItemDetail;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.entity.OrderItem;
import com.aoe.astalift.order.entity.OrderStatusHistory;
import com.aoe.astalift.order.repository.OrderRepository;
import com.aoe.astalift.order.service.BuyerShoppingService;
import com.aoe.astalift.order.util.OrderDtoUtil;
import com.aoe.astalift.order.util.OrderNoGenerator;
import com.aoe.astalift.order.util.OrderStatusHistoryUtil;
import com.aoe.astalift.product.dto.ProductDetail;
import com.aoe.astalift.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by joey on 16-3-22.
 */
@Service("buyerShoppingService")
public class BuyerShoppingServiceImpl implements BuyerShoppingService {

    private static Logger logger = LoggerFactory.getLogger(BuyerShoppingServiceImpl.class);

    @Resource
    OrderRepository orderRepository;

    @Resource
    ProductService productService;

    @Resource
    ProfileService profileService;

    public BaseResponse<OrderDetail> orderProducts(List<OrderItemDto> orderItemDtos, Integer buyerId, Integer supplierId, String receiveAddress, String mobile) {

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
        for (OrderItemDto orderItemDto : orderItemDtos) {
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

    public BaseResponse<OrderDetail> confirmDelivered(String orderNo) {
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderDetail>(new OrderError.OrderNotExist());
        }

        //只能是发货后进行确认收货
        if(byOrderNo.getCurrentStatus() != OrderStatus.DELIVERING.getCode()){
            return new BaseResponse<OrderDetail>(new OrderError.CannotReceive());
        }

        OrderStatusHistoryUtil.addOrderStatusHistory(byOrderNo,OrderStatus.RECEIVED,"买方确认收货");

        Order save = orderRepository.save(byOrderNo);
        if(null == save){
            return new BaseResponse<OrderDetail>(new CommonErrors.DataBaseError());
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderNo(orderNo);
        return new BaseResponse<OrderDetail>(orderDetail);
    }

    public BaseResponse<OrderDetail> cancelOrder(String orderNo, String msg) {
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderDetail>(new OrderError.OrderNotExist());
        }

        //只能在卖方没有备货前 TODO 讨论其他取消订单的方法
        if(byOrderNo.getCurrentStatus() == OrderStatus.INIT.getCode()
                || byOrderNo.getCurrentStatus() == OrderStatus.CONFIRM.getCode()
                )
        {
            OrderStatusHistoryUtil.addOrderStatusHistory(byOrderNo, OrderStatus.CANCEL, "买方取消订单");
            orderRepository.save(byOrderNo);
            //TODO add data to orderDetail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderNo(orderNo);
            return new BaseResponse<OrderDetail>(orderDetail);
        }else {
            return new BaseResponse<OrderDetail>(new OrderError.CannotCancel());
        }

    }

    public BaseResponse<OrderDetail> getOrderDetail(String orderNo) {
        OrderDetail orderDetail = new OrderDetail();
        Order byOrderNo = orderRepository.findByOrderNo(orderNo);
        if(null == byOrderNo){
            return new BaseResponse<OrderDetail>(new OrderError.OrderNotExist());
        }

        String customerName = this.getCustomerName(byOrderNo.getBuyerId());
        if(null == customerName){
            return new BaseResponse<OrderDetail>(new CommonErrors.UnknownError());
        }
        orderDetail.setCustomerName(customerName);
        orderDetail.setOrderNo(orderNo);
        orderDetail.setTotal(byOrderNo.getTotal());
        orderDetail.setOrderDate(byOrderNo.getCreateTime());

        Set<OrderStatusHistory> orderStatusHistorySet = byOrderNo.getOrderStatusHistories();
        Set<OrderItem> orderItemSet = byOrderNo.getOrderItems();
        List<OrderHistoryDto> orderHistoryDtoList = new LinkedList<OrderHistoryDto>();
        List<OrderItemDetail> orderItemDetailList = new LinkedList<OrderItemDetail>();
        for (OrderStatusHistory orderStatusHistory : orderStatusHistorySet) {
            OrderHistoryDto dto = new OrderHistoryDto();
            dto.setStatus(orderStatusHistory.getStatus());
            dto.setCreateTime(orderStatusHistory.getCreateTime());
            dto.setDesc(orderStatusHistory.getDesc());
            orderHistoryDtoList.add(dto);
        }

        for (OrderItem orderItem : orderItemSet) {
            OrderItemDetail orderItemDetail = new OrderItemDetail();
            orderItemDetail.setAmount(orderItem.getAmount());
            orderItemDetail.setOriginalPrice(orderItem.getPrice());
            orderItemDetail.setDiscount(orderItem.getDiscount());
            orderItemDetail.setProductId(orderItem.getProductId());
            BaseResponse<ProductDetail> productDetailBaseResponse = productService.getProductDetail(orderItem.getProductId());
            if(!ResponseUtil.isResponseSuccess(productDetailBaseResponse)){
                logger.error("product id {] 对应的数据有误.", orderItem.getProductId());
                return new BaseResponse<OrderDetail>(new CommonErrors.UnknownError());
            }
            orderItemDetail.setProductTitleImage(productDetailBaseResponse.getData().getTitleImage());
            orderItemDetail.setProductTitle(productDetailBaseResponse.getData().getTitle());
            orderItemDetailList.add(orderItemDetail);
        }
        orderDetail.setHistories(orderHistoryDtoList);
        orderDetail.setOrderItemDetailList(orderItemDetailList);

        return new BaseResponse<OrderDetail>(orderDetail);

    }

    public BaseResponse<List<OrderInfoDto>> listOrder(Integer buyerId) {

        if(null == buyerId){
            return new BaseResponse<List<OrderInfoDto>>(new AccountError.UserNotExist());
        }

        List<Order> byBuyerId = orderRepository.findByBuyerId(buyerId);
        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : byBuyerId) {
            ProfileInfo buyerProfile = profileService.getUserProfile(buyerId).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile,supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }
        return new BaseResponse<List<OrderInfoDto>>(orderInfoDtos);
    }


    public BaseResponse<List<OrderInfoDto>> listUnfinishedOrder(Integer buyerId){

        if(null == buyerId){
            return new BaseResponse<List<OrderInfoDto>>(new AccountError.UserNotExist());
        }

        List<Integer> finishedStatusList =  new ArrayList<Integer>();
        finishedStatusList.add(OrderStatus.CANCEL.getCode());
        finishedStatusList.add(OrderStatus.END.getCode());
        finishedStatusList.add(OrderStatus.RECEIVED.getCode());
        List<Order> byBuyerId = orderRepository.findByBuyerIdAndCurrentStatusNotIn(buyerId, finishedStatusList);

        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : byBuyerId) {
            ProfileInfo buyerProfile = profileService.getUserProfile(buyerId).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile,supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }
        return new BaseResponse<List<OrderInfoDto>>(orderInfoDtos);

    }

    public BaseResponse<List<OrderInfoDto>> listFinishedOrders(Integer buyerId) {
        if(null == buyerId){
            return new BaseResponse<List<OrderInfoDto>>(new AccountError.UserNotExist());
        }
        List<Order> byBuyerIdAndCurrentStatus = orderRepository.findByBuyerIdAndCurrentStatus(buyerId, OrderStatus.END.getCode());
        List<OrderInfoDto> orderInfoDtos = new LinkedList<OrderInfoDto>();
        for (Order order : byBuyerIdAndCurrentStatus) {
            ProfileInfo buyerProfile = profileService.getUserProfile(buyerId).getData();
            ProfileInfo supplierProfile = profileService.getUserProfile(order.getSupplierId()).getData();
            OrderInfoDto orderInfoDto = OrderDtoUtil.getOrderInfoDto(order, buyerProfile,supplierProfile);
            orderInfoDtos.add(orderInfoDto);
        }
        return new BaseResponse<List<OrderInfoDto>>(orderInfoDtos);
    }


    private String getCustomerName(Integer userId){
        BaseResponse<ProfileInfo> userProfile = profileService.getUserProfile(userId);
        if(false == ResponseUtil.isResponseSuccess(userProfile)){
            logger.error("userId {} 不存在该用户. 可能是数据同步的问题， 请检查数据库",userId);
            return null;
        }
        return userProfile.getData().getRealName();
    }


}
