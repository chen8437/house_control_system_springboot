package com.yechrom.cloud.quartz;

import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.mapper.BuyHouseMapper;
import com.yechrom.cloud.dto.mapper.SellHouseMapper;
import com.yechrom.cloud.dto.pojo.BuyHouse;
import com.yechrom.cloud.dto.pojo.BuyHouseExample;
import com.yechrom.cloud.dto.pojo.SellHouse;
import com.yechrom.cloud.dto.pojo.SellHouseExample;
import com.yechrom.cloud.dto.vo.response.ResponseVo;
import com.yechrom.cloud.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class GetData {

    @Resource
    BuyHouseMapper buyHouseMapper;

    @Resource
    SellHouseMapper sellHouseMapper;

    @Autowired
    RedisUtil redis;

    public void run(){

        BuyHouseExample buyExample = new BuyHouseExample();
        buyExample.setCount(null);
        buyExample.setStart(null);
        BuyHouseExample.Criteria buyCriteria = buyExample.createCriteria();
        buyCriteria.andBuyStatueEqualTo(2);

        List<BuyHouse> buyHouses = buyHouseMapper.selectByExample(buyExample);

        SellHouseExample sellExample = new SellHouseExample();
        sellExample.setCount(null);
        sellExample.setStart(null);
        SellHouseExample.Criteria sellCriteria = sellExample.createCriteria();
        sellCriteria.andSellStatueEqualTo(2);

        List<SellHouse> sellHouses = sellHouseMapper.selectByExample(sellExample);

        //获取总成交额度
        long buyCount = 0;
        if(buyHouses.size() != 0){
            for (BuyHouse buyHouse :
                    buyHouses) {
                buyCount += buyHouse.getBuyPrice();
            }
        }
        long sellCount = 0;
        if(buyHouses.size() != 0){
            for (SellHouse sellHouse :
                    sellHouses) {
                buyCount += sellHouse.getSellPrice();
            }
        }
        long countMoney = sellCount + buyCount;

        //获取最火房源
        SellHouseExample fireExample = new SellHouseExample();
        sellExample.setCount(null);
        sellExample.setStart(null);
        SellHouseExample.Criteria fireCriteria = sellExample.createCriteria();
        fireCriteria.andSellStatueEqualTo(1);
        fireCriteria.andIsDeleteEqualTo(0);

        List<SellHouse> fireHouse = sellHouseMapper.selectByExample(fireExample);

        String address = "-";
        int pick = 0;

        if (fireHouse.size() != 0) {
            address = fireHouse.get(0).getSellAddress();
            pick = fireHouse.get(0).getSellPick();
        }

        JSONObject data = new JSONObject();
        data.put("firePick" , pick);
        data.put("address" , address);
        data.put("countMoney", countMoney);

        redis.set("house-control-system-data-dashboard" , data.toJSONString());

        log.info("~定时任务结束~ 结束报文为{}" ,data.toJSONString());
    }
}
