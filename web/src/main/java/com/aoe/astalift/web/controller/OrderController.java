package com.aoe.astalift.web.controller;

import com.aoe.astalift.account.constants.AccountError;
import com.aoe.astalift.account.dto.AccountInfo;
import com.aoe.astalift.account.dto.ProfileInfo;
import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.cart.service.CartService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import com.aoe.astalift.common.dto.ResponseUtil;
import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.dto.request.OrderActionDto;
import com.aoe.astalift.order.dto.request.OrderItemDto;
import com.aoe.astalift.order.dto.response.OrderDetail;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.entity.Order;
import com.aoe.astalift.order.service.BuyerShoppingService;
import com.aoe.astalift.order.service.OrderService;
import com.aoe.astalift.order.service.SupplierOrderProcessService;
import com.aoe.astalift.web.annotation.SessionValidIgnore;
import com.aoe.astalift.web.constants.SessionConstants;
import com.aoe.astalift.web.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.primitives.Booleans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by joey on 16-3-16.
 */
@RestController
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    SessionService sessionService;

    @Resource
    BuyerShoppingService buyerShoppingService;

    @Resource
    ProfileService profileService;

    @Resource
    AccountService accountService;

    @Resource
    CartService cartService;

    @Resource
    SupplierOrderProcessService supplierOrderProcessService;

    @Resource
    OrderService orderService;

    @Resource
    ObjectMapper objectMapper;

    @RequestMapping(value = "/order/{orderNo}", method = RequestMethod.GET)
    BaseResponse getDetail(@PathVariable String orderNo, HttpSession session){
        String sessionKey = (String) session.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        return orderService.getOrderDetail(orderNo);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    BaseResponse addOrder(@RequestBody List<OrderItemDto> orderItemDtoList, HttpSession httpSession){
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        Integer supplierId = null;
        //TODO 先写死代理关系. 后续需要添加服务
        if(userId == 3){
            supplierId = 2;
        }else if( userId == 2){
            supplierId = 1;
        }
        String address = profileService.getUserProfile(userId).getData().getAddress();
        String mobile = profileService.getUserProfile(userId).getData().getMobile();
        return buyerShoppingService.orderProducts(orderItemDtoList, userId, supplierId, address, mobile);
    }


    // 修改订单状态
    @RequestMapping(value = "/order/{orderNo}", method = RequestMethod.PUT)
    public BaseResponse orderStatus(
            HttpSession httpSession,
            @PathVariable String orderNo,
            @RequestBody OrderActionDto orderAction)
    {

        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        BaseResponse<OrderDetail> detailBaseResponse = orderService.getOrderDetail(orderNo);
        OrderDetail orderDetail = detailBaseResponse.getData();
        logger.debug("userId {}, orderId {}, currentStatus {}, actionType",userId,orderNo,detailBaseResponse.getData().getStatusDesc(),orderAction.getActionType());

        if(!ResponseUtil.isResponseSuccess(detailBaseResponse)){
            return detailBaseResponse;
        }
        BaseResponse<AccountInfo> accountInfoResponse = accountService.getAccountInfo(userId);
        if(!ResponseUtil.isResponseSuccess(accountInfoResponse)){
            return accountInfoResponse;
        }

        AccountInfo accountInfo = accountInfoResponse.getData();
        List<String> roles = accountInfo.getRoles();

        Boolean asBuyer = null;
        Boolean asSeller = null;

        if(0 == orderDetail.getCustomerName().compareTo(accountInfo.getUserName())){
            asBuyer = new Boolean(true);
            asSeller = new Boolean(false);
        }

        if(0 == orderDetail.getSupplierName().compareTo(accountInfo.getUserName())){
            asSeller = new Boolean(true);
            asBuyer = new Boolean(false);
        }

        if(null== asBuyer && null == asSeller){
            logger.error("orderId {}, userId {} 既不是买家， 也不是卖家", orderNo,userId);
        }


        switch (orderAction.getActionType()){
            case "accept":
                if(asSeller == false){
                    return new BaseResponse(new AccountError.InvalidUserRole());
                }
                return orderService.setOrderStatus(orderNo, OrderStatus.CONFIRM, "供应方完成备货,等待买方付款");
            case "cancel":
                if(asSeller == false){
                    return new BaseResponse(new AccountError.InvalidUserRole());
                }
                return orderService.setOrderStatus(orderNo, OrderStatus.REFUSE, "货源不足， 卖家取消订单");
            case "paid":
                if(asSeller == false){
                    return new BaseResponse(new AccountError.InvalidUserRole());
                }
                return orderService.setOrderStatus(orderNo,OrderStatus.PAID,"供应方已收到全部货款");
            case "deliver":
                if(asSeller == false){
                    return new BaseResponse(new AccountError.InvalidUserRole());
                }
                return orderService.setOrderStatus(orderNo,OrderStatus.DELIVERING,"已发货, 承运公司: "+orderAction.getDeliveryInfo().getCompany() +", 运单号：" +orderAction.getDeliveryInfo().getDeliveryNo());
            case "receive":
                if(asBuyer == false){
                    return new BaseResponse(new AccountError.InvalidUserRole());
                }
                return orderService.setOrderStatus(orderNo,OrderStatus.RECEIVED,"买家已收货");
            default:
                return new BaseResponse(new CommonErrors.UnknownError());
        }

    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public BaseResponse<List<OrderInfoDto>> listOrder(HttpSession httpSession,
                                        @RequestParam("status") Integer orderStatus,@RequestParam(value = "role",required = false) String asRole)
    {
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        AccountInfo accountInfo = accountService.getAccountInfo(userId).getData();
        if(null == accountInfo){
            logger.error("数据库中无法获取账号信息， userId {}",userId);
            return new BaseResponse<List<OrderInfoDto>>(new CommonErrors.UnknownError());
        }
        logger.debug("userId {} status {}",userId,orderStatus);

        List<OrderInfoDto> orders = new ArrayList<>();
        List<String> roles = accountInfo.getRoles();
        //排除重复
        Map<String,OrderInfoDto> orderMap = new HashMap<>();

        for (String role : roles) {

            if(null != asRole && false == asRole.equals(role)){
                continue;
            }

            if(role.equals("buy")){

                BaseResponse<List<OrderInfoDto>> listBaseResponse = new BaseResponse<>();
                if(orderStatus != 0){
                    listBaseResponse = buyerShoppingService.listOrder(userId, orderStatus);
                }else{
                    listBaseResponse = buyerShoppingService.listUnfinishedOrder(userId);
                }

                List<OrderInfoDto> data = listBaseResponse.getData();
                for (OrderInfoDto orderInfoDto : data) {

                    if(null == orderMap.get(orderInfoDto.getOrderNo())){
                        orderMap.put(orderInfoDto.getOrderNo(),orderInfoDto);
                    }
                }
            }

            if(role.equals("sell")){
                BaseResponse<List<OrderInfoDto>> listBaseResponse = new BaseResponse<>();
                if(orderStatus != 0){
                    listBaseResponse = supplierOrderProcessService.listOrder(userId, orderStatus);
                } else{
                    listBaseResponse = supplierOrderProcessService.listUnfinishedOrders(userId);
                }
                List<OrderInfoDto> data = listBaseResponse.getData();
                for (OrderInfoDto orderInfoDto : data) {
                    if(null == orderMap.get(orderInfoDto.getOrderNo())){
                        orderMap.put(orderInfoDto.getOrderNo(),orderInfoDto);
                    }
                }
            }
        }

        return new BaseResponse<List<OrderInfoDto>>(new ArrayList<>(orderMap.values()));
    }



    private boolean checkRole(Integer userId, String role){
        BaseResponse<AccountInfo> accountInfo = accountService.getAccountInfo(userId);
        if(!ResponseUtil.isResponseSuccess(accountInfo)){
            logger.error("检查用户权限失败, {} 对应的用户不存在", accountInfo);
            return false;
        }

        //检查buy权限
        List<String> roles = accountInfo.getData().getRoles();
        for (String s : roles) {
            if(s.equals(role)){
                return true;
            }
        }

        return false;
    }

}
