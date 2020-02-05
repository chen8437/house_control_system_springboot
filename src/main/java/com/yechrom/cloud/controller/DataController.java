package com.yechrom.cloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.dto.vo.response.ResponseVo;
import com.yechrom.cloud.interceptor.CheckToken;
import com.yechrom.cloud.service.DataService;
import com.yechrom.cloud.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CheckToken
@RestController
@Slf4j
public class DataController {


    @Autowired
    private DataService dataService;

    @Autowired
    private RedisUtil redis;
    /**
     *  点击了求租房源
     * @param
     * @return
     */
    @GetMapping("/bigdata/pickbuy")
    @ResponseBody
    public ResponseBaseVo pickBuy(@RequestParam(value = "order")String order){

        log.info("调用了接口 点击了某求租订单, 传入报文为: {} " , order );

        ResponseBaseVo response = dataService.pickBuy(order);

        return response;
    }

    /**
     *  点击了求租房源
     * @param
     * @return
     */
    @GetMapping("/bigdata/picksell")
    @ResponseBody
    public ResponseBaseVo pickSell(@RequestParam(value = "order")String order){

        log.info("调用了接口 点击了某在租订单, 传入报文为: {} " , order );

        ResponseBaseVo response = dataService.pickSell(order);

        return response;
    }


    /**
     *  获取实时数据
     * @param
     * @return
     */
    @GetMapping("/bigdata/getdata")
    @ResponseBody
    public ResponseBaseVo getdata(){

        log.info("调用了接口,获取首页实时数据" );

        String result = redis.get("house-control-system-data-dashboard");

        ResponseVo response = new ResponseVo();
        response.setErrorcode(1);

        if (!StringUtils.isNotBlank(result)){
            JSONObject dataNull = new JSONObject();
            dataNull.put("firePick" , 0);
            dataNull.put("address" , "-");
            dataNull.put("countMoney" , 0);
            response.setData(dataNull);
            return response;
        }

        JSONObject data = JSONObject.parseObject(result);
        response.setData(data);

        return response;
    }




}
