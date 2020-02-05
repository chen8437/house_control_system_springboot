package com.yechrom.cloud.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * uuid的工具类
 */
public class UUIDUtil {

    //获取32位uuid(用户的)
    public static String getUserUUID(){
        return UUID.randomUUID().toString().replaceAll("-" , "");
    }

    //制作订单号
    public static String getOrderID(Order order){
        String result = "";

        //生成随机字符串
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random1=new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 8; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number=random1.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        String randomStr = String.valueOf(sb);

        //获得时间戳
        String time = String.valueOf(new Date().getTime());

        //制作
        if (order == Order.SELLER){
            result = "order-sell-" + time + randomStr;
        } else if(order == Order.USER ){
            result = "order-buyy-" + time + randomStr;
        } else if(order == Order.QUESTION ){
            result = "order-ques-" + time + randomStr;
        }
        return result;
    }

    /**
     * 自定义枚举类
     */
    public enum Order {
        SELLER , USER , QUESTION
    }
}
