package com.yechrom.cloud.controller;

import com.yechrom.cloud.dto.vo.AfterOrderAddVo;
import com.yechrom.cloud.dto.vo.ShowAllSellHouseVo;
import com.yechrom.cloud.dto.vo.ShowMeAllOrderVo;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.interceptor.CheckToken;
import com.yechrom.cloud.service.AfterOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CheckToken
public class AfterOrderController {


    @Autowired
    AfterOrderService afterOrderService;

    /**
     * 查看某user所有订单
     * @param
     * @return
     */
    @PostMapping("/afterorder/put")
    @ResponseBody
    public ResponseBaseVo addAfterOrder(@RequestBody AfterOrderAddVo requestVo) throws Exception {

        log.info("调用了添加售后单接口 , 传入报文为: {}" , requestVo);

        ResponseBaseVo response = afterOrderService.addAfterOrder(requestVo);

        return response;
    }


    /**
     * 查看所有问题单
     * @param
     * @return
     */
    @PostMapping("/afterorder/show")
    @ResponseBody
    public ResponseBaseVo showAfterOrder(@RequestBody ShowAllSellHouseVo requestVo) throws Exception {

        log.info("调用了查看所有问题单接口 , 传入报文为: {}" , requestVo);

        ResponseBaseVo response = afterOrderService.showAfterOrder(requestVo);

        return response;
    }


    /**
     * 修改问题单状态
     * @param
     * @return
     */
    @GetMapping("/afterorder/changed")
    @ResponseBody
    public ResponseBaseVo changeAfterOrder(@RequestParam(value = "order") String order , @RequestParam(value = "statue") int statue ) throws Exception {

        log.info("调用了修改问题单状态的接口 , 传入报文为: {} , {}" , order , statue);

        ResponseBaseVo response = afterOrderService.changeAfterOrder(order , statue);

        return response;
    }
}
