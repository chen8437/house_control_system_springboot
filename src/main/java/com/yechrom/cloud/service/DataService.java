package com.yechrom.cloud.service;

import com.yechrom.cloud.dto.mapper.BuyHouseMapper;
import com.yechrom.cloud.dto.mapper.SellHouseMapper;
import com.yechrom.cloud.dto.pojo.BuyHouse;
import com.yechrom.cloud.dto.pojo.BuyHouseExample;
import com.yechrom.cloud.dto.pojo.SellHouse;
import com.yechrom.cloud.dto.pojo.SellHouseExample;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.util.ResponseVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class DataService {


    @Resource
    private SellHouseMapper sellHouseMapper;

    @Resource
    private BuyHouseMapper buyHouseMapper;

    /**
     * 给求租订单+1
     * @param order
     * @return
     */
    public ResponseBaseVo pickBuy(String order){

        BuyHouseExample example = new BuyHouseExample();
        BuyHouseExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andBuyOrderEqualTo(order);
        List<BuyHouse> buyHouses = buyHouseMapper.selectByExample(example);

        if (buyHouses.size() > 0 ){
            int pick = buyHouses.get(0).getBuyPick();
            pick++;

            BuyHouse house = new BuyHouse();
            house.setBuyPick(pick);

            int result = buyHouseMapper.updateByExampleSelective(house, example);

            ResponseBaseVo response = ResponseVoUtil.getResponse(result, "成功", "数据采集失败");

            return response;
        }
        return ResponseVoUtil.getResponse(0, "成功", "数据采集失败");
    }


    /**
     * 给在租订单+1
     * @param order
     * @return
     */
    public ResponseBaseVo pickSell(String order){
        SellHouseExample example = new SellHouseExample();
        example.setCount(null);
        example.setStart(null);
        SellHouseExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andSellOrderEqualTo(order);
        List<SellHouse> sellHouses = sellHouseMapper.selectByExample(example);

        if (sellHouses.size() > 0) {
            int pick = sellHouses.get(0).getSellPick();
            pick++;

            SellHouse house = new SellHouse();
            house.setSellPick(pick);

            int result = sellHouseMapper.updateByExampleSelective(house, example);

            ResponseBaseVo response = ResponseVoUtil.getResponse(result, "成功", "数据采集失败");

            return response;
        }
        return ResponseVoUtil.getResponse(0, "成功", "数据采集失败");
    }
}
