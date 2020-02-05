package com.yechrom.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.mapper.BuyHouseMapper;
import com.yechrom.cloud.dto.mapper.SellHouseMapper;
import com.yechrom.cloud.dto.mapper.UserMapper;
import com.yechrom.cloud.dto.pojo.*;
import com.yechrom.cloud.dto.vo.ShowAllHouseBackSellVo;
import com.yechrom.cloud.dto.vo.ShowAllHouseBackVo;
import com.yechrom.cloud.dto.vo.ShowAllSellHouseVo;
import com.yechrom.cloud.dto.vo.ShowMeAllOrderVo;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.dto.vo.response.ResponseVo;
import com.yechrom.cloud.util.ResponseVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MeService {

    @Resource
    private BuyHouseMapper buyHouseMapper;

    @Resource
    private SellHouseMapper sellHouseMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 查询所有的求租订单
     * @param requestVo
     * @return
     */
    public ResponseBaseVo showAllUserHouse(ShowMeAllOrderVo requestVo){

        //查数据库
        BuyHouseExample example = new BuyHouseExample();
        int start = (requestVo.getPage() -1) * requestVo.getLimit();
        example.setStart(start);
        example.setCount(requestVo.getLimit());
        BuyHouseExample.Criteria criteria = example.createCriteria();
        criteria.andBuyUuidEqualTo(requestVo.getUuid());
        criteria.andIsDeleteEqualTo(0);

        List<BuyHouse> buyHouses = buyHouseMapper.selectByExample(example);

        List<ShowAllHouseBackVo> allList = new ArrayList<ShowAllHouseBackVo>();

        //遍历获取vo
        for (BuyHouse buyHouse :
                buyHouses) {
            ShowAllHouseBackVo vo = new ShowAllHouseBackVo();

            vo.setBuy_order(buyHouse.getBuyOrder());
            vo.setBuy_statue(buyHouse.getBuyStatue());
            vo.setBuy_time(buyHouse.getBuyTime());
            vo.setBuy_uuid(buyHouse.getBuyUuid());
            vo.setBuy_address(buyHouse.getBuyAddress());
            vo.setBuy_introduction(buyHouse.getBuyIntroduction());
            vo.setBuy_price(buyHouse.getBuyPrice());
            vo.setBuy_pick(buyHouse.getBuyPick());

            UserExample exampleUser = new UserExample();
            UserExample.Criteria criteriaUser = exampleUser.createCriteria();
            criteriaUser.andUuidEqualTo(buyHouse.getSellUuid());
            criteriaUser.andIsDeleteEqualTo(0);
            List<User> users = userMapper.selectByExample(exampleUser);
            if (users.size() == 0){
                vo.setBuy_name("无");
            }
            vo.setBuy_name(users.get(0).getName());
            allList.add(vo);
        }

        ResponseVo response = new ResponseVo();
        JSONObject data = new JSONObject();
        data.put("list" ,allList);
        data.put("total" ,allList.size());
        response.setData(data);
        response.setErrorcode(1);

        return response;
    }


    /**
     * 关闭某求租订单
     * @param order
     * @return
     */
    public ResponseBaseVo closeUserSellHouse(String order){

        BuyHouseExample example = new BuyHouseExample();
        BuyHouseExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andBuyOrderEqualTo(order);

        BuyHouse house = new BuyHouse();
        house.setBuyStatue(0);

        int result = buyHouseMapper.updateByExampleSelective(house ,example);

        return ResponseVoUtil.getResponse(result , "关闭订单操作成功" , "关闭订单操作失败");
    }

    /**
     * 查询所有的求出租订单
     * @param requestVo
     * @return
     */
    public ResponseBaseVo showAllSellerHouse(ShowMeAllOrderVo requestVo){

        //查数据库
        SellHouseExample example = new SellHouseExample();
        int start = (requestVo.getPage() -1) * requestVo.getLimit();
        example.setStart(start);
        example.setCount(requestVo.getLimit());
        SellHouseExample.Criteria criteria = example.createCriteria();

        criteria.andSellUuidEqualTo(requestVo.getUuid());
        criteria.andIsDeleteEqualTo(0);

        List<SellHouse> sellHouses = sellHouseMapper.selectByExample(example);

        List<ShowAllHouseBackSellVo> allList = new ArrayList<ShowAllHouseBackSellVo>();

        //遍历获取vo
        for (SellHouse sellHouse :
                sellHouses) {
            ShowAllHouseBackSellVo vo = new ShowAllHouseBackSellVo();

            vo.setSell_order(sellHouse.getSellOrder());
            vo.setSell_statue(sellHouse.getSellStatue());
            vo.setSell_time(sellHouse.getSellTime());
            vo.setSell_uuid(sellHouse.getSellUuid());
            vo.setSell_address(sellHouse.getSellAddress());
            vo.setSell_introduction(sellHouse.getSellIntroduction());
            vo.setSell_price(sellHouse.getSellPrice());
            vo.setSell_pick(sellHouse.getSellPick());

            UserExample exampleUser = new UserExample();
            UserExample.Criteria criteriaUser = exampleUser.createCriteria();
            criteriaUser.andUuidEqualTo(sellHouse.getBuyUser());
            criteriaUser.andIsDeleteEqualTo(0);
            List<User> users = userMapper.selectByExample(exampleUser);

            if (users.size() == 0){
                vo.setSell_name("无");
            }
            vo.setSell_name(users.get(0).getName());
            allList.add(vo);
        }

        ResponseVo response = new ResponseVo();
        JSONObject data = new JSONObject();
        data.put("list" ,allList);
        data.put("total" ,allList.size());
        response.setData(data);
        response.setErrorcode(1);

        return response;
    }


    /**
     * 关闭某在租订单
     * @param order
     * @return
     */
    public ResponseBaseVo closeSellUserHouse(String order){

        SellHouseExample example = new SellHouseExample();
        SellHouseExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andSellOrderEqualTo(order);

        SellHouse house = new SellHouse();
        house.setSellStatue(0);

        int result = sellHouseMapper.updateByExampleSelective(house ,example);

        return ResponseVoUtil.getResponse(result , "关闭订单操作成功" , "关闭订单操作失败");
    }
}
