package com.yechrom.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.vo.AddHouseBySellerVo;
import com.yechrom.cloud.dto.vo.BuyHouseHaveASellerVo;
import com.yechrom.cloud.dto.vo.ShowAllSellHouseVo;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.dto.vo.response.ResponseErrorVo;
import com.yechrom.cloud.dto.vo.response.ResponseVo;
import com.yechrom.cloud.interceptor.CheckToken;
import com.yechrom.cloud.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 房源和订单相关的controller
 */
@CheckToken
@RestController
@Slf4j
public class HouseController {

    @Autowired
    HouseService houseService;

    /**
     * 房东添加房源
     * @param requestVo
     * @return
     */
    @PostMapping("/putorder/seller")
    @ResponseBody
    public ResponseBaseVo sellerAddHouse(@RequestBody AddHouseBySellerVo requestVo){

        log.info("某seller调用了添加房源接口 , 传入报文为: {}" , requestVo);

        int result = houseService.addHouseBySeller(requestVo);

        ResponseBaseVo response = houseService.getResponse(result ,"发布成功" , "发布失败");

        return response;
    }


    /**
     * 顾客添加房源
     * @param requestVo
     * @return
     */
    @PostMapping("/putorder/user")
    @ResponseBody
    public ResponseBaseVo userAddHouse(@RequestBody AddHouseBySellerVo requestVo){

        log.info("某user调用了添加房源接口 , 传入报文为: {}" , requestVo);

        int result = houseService.addHouseByUser(requestVo);

        ResponseBaseVo response = houseService.getResponse(result , "发布成功" , "发布失败");

        return response;
    }


    /**
     * 查看所有求租房源
     * @param requestVo
     * @return
     */
    @PostMapping("/housebuy/show")
    @ResponseBody
    public ResponseBaseVo showHouseByUser(@RequestBody ShowAllSellHouseVo requestVo){

        log.info("调用了展示求购房源接口 , 传入报文为: {}" , requestVo);

        ResponseBaseVo response = houseService.showAllBuyHouse(requestVo);

        return response;
    }

    /**
     *  求租房源被房东卖了
     * @param
     * @return
     */
    @GetMapping("/housebuy/get")
    @ResponseBody
    public ResponseBaseVo BuyHouseHaveASeller(@RequestParam(value = "sell_order")String sellorder ,@RequestParam(value = "sell_id")String sellid){

        log.info("某Seller调用了接口想要给求租人卖房 , 传入报文为: {} , {}" , sellorder , sellid);

        ResponseBaseVo response = houseService.BuyHouseHaveASeller(sellorder , sellid);

        return response;
    }


    /**
     * 查看所有在租
     * @param requestVo
     * @return
     */
    @PostMapping("/housesell/show")
    @ResponseBody
    public ResponseBaseVo showHouseBySeller(@RequestBody ShowAllSellHouseVo requestVo){

        log.info("调用了展示在租房源接口 , 传入报文为: {}" , requestVo);

        ResponseBaseVo response = houseService.showAllSellHouse(requestVo);

        return response;
    }

    /**
     *  在租房源被顾客,买了
     * @param
     * @return
     */
    @GetMapping("/housesell/get")
    @ResponseBody
    public ResponseBaseVo BuyHouseHaveAUser(@RequestParam(value = "buy_order")String buyorder ,@RequestParam(value = "buy_id")String buyid){

        log.info("某User调用了接口想要租别人的房 , 传入报文为: {} , {}" , buyorder , buyid);

        ResponseBaseVo response = houseService.BuyHouseHaveAUser(buyorder , buyid);

        return response;
    }

}
