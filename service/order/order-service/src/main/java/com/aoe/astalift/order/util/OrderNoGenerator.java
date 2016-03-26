package com.aoe.astalift.order.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by joey on 16-3-23.
 */
public class OrderNoGenerator {

    static AtomicInteger seq = new AtomicInteger(1);

    public static String generate(){
        String sn = null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddhhmm");
        String dateString = simpleDateFormat.format(now);
        String numString = null;
        int num = seq.getAndIncrement();
        if(num > 99){
            seq.set(1);
            num = 0;
        }
        if( num >= 0 && num <= 9 ){
            numString = "0" + num;
        }else {
            numString = new Integer(num).toString();
        }
        sn = dateString + "-" + numString;
        return sn;
    }
}
