package com.aoe.astalift.order.test;

import com.aoe.astalift.order.constants.OrderStatus;
import com.aoe.astalift.order.util.OrderNoGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by joey on 16-3-16.
 */
public class Foo {

    private static Logger logger = LoggerFactory.getLogger(Foo.class);

    @Test
    public void testCreateTime(){

        for (int i = 0; i < 100; i++) {
            Date date = createTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = formatter.format(date);
            logger.info("==>"+format);
        }
    }

    @Test
    public void testOrderNoGen(){
        for (int i = 0; i < 200; i++) {
            String sn = OrderNoGenerator.generate();
            logger.debug(sn);
        }

    }


    @Test
    public void testOrderStatusDesc(){
        String statusCodeDesc = OrderStatus.getStatusCodeDesc(1);
        System.out.println(statusCodeDesc);
    }

    //产生过去30天的随机时间
    public static Date createTime(){
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
