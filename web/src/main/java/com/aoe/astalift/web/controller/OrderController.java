package com.aoe.astalift.web.controller;

import com.aoe.astalift.account.constants.AccountError;
import com.aoe.astalift.account.dto.AccountInfo;
import com.aoe.astalift.account.service.AccountService;
import com.aoe.astalift.account.service.ProfileService;
import com.aoe.astalift.cart.service.CartService;
import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.common.dto.CommonErrors;
import com.aoe.astalift.common.dto.ResponseUtil;
import com.aoe.astalift.order.dto.request.OrderActionDto;
import com.aoe.astalift.order.dto.request.OrderItemDto;
import com.aoe.astalift.order.dto.response.OrderInfoDto;
import com.aoe.astalift.order.service.BuyerShoppingService;
import com.aoe.astalift.order.service.SupplierOrderProcessService;
import com.aoe.astalift.web.constants.SessionConstants;
import com.aoe.astalift.web.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping(value = "/buy/order",method = RequestMethod.POST)
    BaseResponse orderProducts(HttpSession httpSession, @RequestBody List<OrderItemDto> orderItemDtoList){
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
        return buyerShoppingService.orderProducts(orderItemDtoList,userId,supplierId,address,mobile);
    }


    @RequestMapping(value = "/buy/unfinished_orders",method = RequestMethod.GET)
    BaseResponse buyerUnfinishedOrders(HttpSession httpSession){
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        if(!checkRole(userId,"buy")){
            return new BaseResponse(new AccountError.PermissionDenied());
        }
        //load 订单
        BaseResponse<List<OrderInfoDto>> listBaseResponse = buyerShoppingService.listUnfinishedOrder(userId);
        if(listBaseResponse.getCode() == 0){
            cartService.deleteUserCart(userId);
        }
        return listBaseResponse;
    }

    @RequestMapping(value = "/buy/finished_orders",method = RequestMethod.GET)
    BaseResponse buyerFinishedOrders(HttpSession httpSession){
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        if(!checkRole(userId,"buy")){
            return new BaseResponse(new AccountError.PermissionDenied());
        }
        return buyerShoppingService.listFinishedOrders(userId);
    }


    @RequestMapping(value = "/sell/unAccepted_orders",method = RequestMethod.GET)
    public BaseResponse sellerUnAcceptedOrders(HttpSession httpSession){
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        if(!checkRole(userId,"sell")){
            return new BaseResponse(new AccountError.PermissionDenied());
        }

        return supplierOrderProcessService.listUnAcceptedOrders(userId);
    }

    @RequestMapping(value = "/sell/unFinished_orders", method = RequestMethod.GET)
    public BaseResponse sellerUnfinishedOrders(HttpSession httpSession){
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        if(!checkRole(userId,"sell")){
            return new BaseResponse(new AccountError.PermissionDenied());
        }
        return supplierOrderProcessService.listUnfinishedOrders(userId);
    }


    @RequestMapping(value = "/sell/finished_orders", method = RequestMethod.GET)
    public BaseResponse sellerFinishedOrders(HttpSession httpSession){
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);
        if(!checkRole(userId,"sell")){
            return new BaseResponse(new AccountError.PermissionDenied());
        }
        return supplierOrderProcessService.listFinishedOrders(userId);
    }

    @RequestMapping(value = "/sell/order/{orderNo}", method = RequestMethod.PUT)
    public BaseResponse orderStatus(HttpSession httpSession,@PathVariable String orderNo, @RequestBody OrderActionDto orderAction){
        String sessionKey = (String) httpSession.getAttribute(SessionConstants.SESSION_KEY);
        Integer userId = sessionService.getUserId(sessionKey);

        switch (orderAction.getActionType()){
            case "accept":
                return supplierOrderProcessService.confirmOrder(orderNo);
            case "cancel":
                return supplierOrderProcessService.cancelOrder(orderNo, "货源不足， 卖家取消订单");
            case "finish":
                return supplierOrderProcessService.finishOrder(orderNo);
            default:
                return new BaseResponse(new CommonErrors.UnknownError());
        }

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
