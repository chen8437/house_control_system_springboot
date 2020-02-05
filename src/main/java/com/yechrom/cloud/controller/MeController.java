package com.yechrom.cloud.controller;

import com.yechrom.cloud.dto.vo.ShowAllSellHouseVo;
import com.yechrom.cloud.dto.vo.ShowMeAllOrderVo;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.interceptor.CheckToken;
import com.yechrom.cloud.service.MeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CheckToken
public class MeController {


    @Autowired
    MeService meService;

    /**
     * 查看某user所有订单
     * @param
     * @return
     */
    @PostMapping("/me/user/get")
    @ResponseBody
    public ResponseBaseVo showHouseByUser(@RequestBody ShowMeAllOrderVo requestVo){

        log.info("调用了展示用户所有订接口 , 传入报文为: {}" , requestVo);

        ResponseBaseVo response = meService.showAllUserHouse(requestVo);

        return response;
    }

    /**
     * 取消某在租订单
     * @param
     * @return
     */
    @GetMapping("/me/user/close")
    @ResponseBody
    public ResponseBaseVo closeUserSellHouse(@RequestParam(value = "order") String order){

        log.info("调用了取消某在租订单接口 , 传入报文为: {}" , order);

        ResponseBaseVo response = meService.closeUserSellHouse(order);

        return response;
    }



    /**
     * 查看某seller所有订单
     * @param
     * @return
     */
    @PostMapping("/me/seller/get")
    @ResponseBody
    public ResponseBaseVo showHouseBySeller(@RequestBody ShowMeAllOrderVo requestVo){

        log.info("调用了展示用户所有订接口 , 传入报文为: {}" , requestVo);

        ResponseBaseVo response = meService.showAllSellerHouse(requestVo);

        return response;
    }



    /**
     * 取消某求租订单
     * @param
     * @return
     */
    @GetMapping("/me/seller/close")
    @ResponseBody
    public ResponseBaseVo closeSellerSellHouse(@RequestParam(value = "order") String order){

        log.info("调用了取消某在租订单接口 , 传入报文为: {}" , order);

        ResponseBaseVo response = meService.closeSellUserHouse(order);

        return response;
    }

}
