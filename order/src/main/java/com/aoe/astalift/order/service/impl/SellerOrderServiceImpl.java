package com.aoe.astalift.order.service.impl;

import com.aoe.astalift.common.dto.BaseResponse;
import com.aoe.astalift.order.dto.OrderInfoDto;
import com.aoe.astalift.order.service.SellerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by joey on 16-3-16.
 */
@Service("sellerOrderService")
public class SellerOrderServiceImpl implements SellerOrderService {

    private static Logger logger = LoggerFactory.getLogger(SellerOrderServiceImpl.class);

    public BaseResponse<List<OrderInfoDto>> listOrderByCustomer(String customerId) {
        logger.debug("list seller orders for customer : ");

        List<OrderInfoDto> lit = new LinkedList<OrderInfoDto>();
        for (int i = 0; i < 4; i++) {
            OrderInfoDto orderInfoDto = new OrderInfoDto();
            Date date = new Date();
            orderInfoDto.setCompleteTime(date);
        }
        return null;
    }

    public BaseResponse<List<OrderInfoDto>> listOrderByCustomerAndStatus(String customerId, Integer status){
        List<OrderInfoDto> list = new LinkedList<OrderInfoDto>();
        for (int i = 0; i < 20; i++) {
            OrderInfoDto orderInfoDto = new OrderInfoDto();
            orderInfoDto.setStatus(0);
            orderInfoDto.setOrderDate(createTime());
            if(i%3 == 0){
                orderInfoDto.setCustomerName("张三");
                orderInfoDto.setDeliverAddress("浦东新区陆家嘴环路1333号中国平安金融大厦");
            }else{
                orderInfoDto.setCustomerName("李四");
                orderInfoDto.setDeliverAddress("上海市浦东新区陆家嘴银城中路600弄1-10号");
            }
            List<String> contents = new LinkedList<String>();
            float total = 0;
            if(i%2 == 0){
                String ys = "艾诗缇 眼霜精华 15ml X 20个 X 88.00 X 85%";
                String xmn = "艾诗缇 洗面奶 200ml X 30个 X 128.00 X 85%";
                float ysTotal = (float) (15 * 20 * 88.00 * 0.85f);
                float xmnTotal = (float)(30 * 128.00 * 0.85f);
                total = ysTotal + xmnTotal;
                contents.add(ys);
                contents.add(xmn);
            }else{
                String gls = "艾诗缇 隔离霜 100ml X 21个 X 68.00 X 75%";
                total = (float) (21 * 68.00 * 0.75f);
                contents.add(gls);
            }
            orderInfoDto.setOrderContent(contents);
            orderInfoDto.setTotal(total);
            list.add(orderInfoDto);
        }
        return new BaseResponse<List<OrderInfoDto>>(list);
    }


    //产生过去30天的随机时间
    private static Date createTime(){
        int randomDay = new Random().nextInt(30);
        int randomHour = new Random().nextInt(24);
        int randomMin = new Random().nextInt(60);
        int randomSeconds = new Random().nextInt(60);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0 - randomDay);
        calendar.add(Calendar.HOUR_OF_DAY, 0 - randomHour);
        calendar.add(Calendar.MINUTE,0-randomMin);
        calendar.add(Calendar.SECOND, 0-randomSeconds);
        return calendar.getTime();

    }
}
