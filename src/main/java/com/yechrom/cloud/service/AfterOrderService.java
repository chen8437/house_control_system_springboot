package com.yechrom.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.mapper.QuestionOrderMapper;
import com.yechrom.cloud.dto.pojo.QuestionOrder;
import com.yechrom.cloud.dto.pojo.QuestionOrderExample;
import com.yechrom.cloud.dto.pojo.SellHouse;
import com.yechrom.cloud.dto.vo.AfterOrderAddVo;
import com.yechrom.cloud.dto.vo.ShowAfterOrderBackVo;
import com.yechrom.cloud.dto.vo.ShowAllSellHouseVo;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.dto.vo.response.ResponseVo;
import com.yechrom.cloud.exception.exceptions.ParamIsNullException;
import com.yechrom.cloud.util.ResponseVoUtil;
import com.yechrom.cloud.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AfterOrderService {

    @Resource
    QuestionOrderMapper questionOrderMapper;


    /**
     * 添加售后单
     * @param request
     * @return
     */
    public ResponseBaseVo addAfterOrder(AfterOrderAddVo request) throws Exception {

        if( !StringUtils.isNotBlank(request.getAddress()) || !StringUtils.isNotBlank(request.getIntroduction()) || !StringUtils.isNotBlank(request.getPhone()) || !StringUtils.isNotBlank(request.getSeller())){
            throw new ParamIsNullException("参数为空");
        }

        QuestionOrder quest = new QuestionOrder();

        quest.setQuesOrder(UUIDUtil.getOrderID(UUIDUtil.Order.QUESTION));
        quest.setQuesTime(new Date());
        quest.setQuesStatue(1);
        quest.setQuesAddress(request.getAddress());
        quest.setQuesIntruduction(request.getIntroduction());
        quest.setQuesSeller(request.getSeller());
        quest.setIsDelete(0);
        quest.setFlag1(request.getPhone());

        int result = questionOrderMapper.insertSelective(quest);
        return ResponseVoUtil.getResponse(result , "售后单提交成功" , "售后单提交失败");
    }

    /**
     *  查看所有问题单
     * @param request
     * @return
     */
    public ResponseBaseVo showAfterOrder(ShowAllSellHouseVo request) throws Exception {

        if( request.getLimit() == 0 ){
            throw new ParamIsNullException("参数错误");
        }

        //查数据库
        QuestionOrderExample example = new QuestionOrderExample();
        int start = (request.getPage() -1) * request.getLimit();
        example.setStart(start);
        example.setCount(request.getLimit());
        QuestionOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);

        List<QuestionOrder> orders = questionOrderMapper.selectByExample(example);

        List<ShowAfterOrderBackVo> allList = new ArrayList<ShowAfterOrderBackVo>();

        //遍历获取vo
        for (QuestionOrder order :
                orders) {
            ShowAfterOrderBackVo vo = new ShowAfterOrderBackVo();

            vo.setAfter_order(order.getQuesOrder());
            vo.setAfter_statue(order.getQuesStatue());
            vo.setAfter_introduction(order.getQuesIntruduction());
            vo.setAfter_phone(order.getFlag1());
            vo.setAfter_time(order.getQuesTime());
            vo.setAfter_seller(order.getQuesSeller());

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
     * 修改问题单状态
     * @param request
     * @return
     * @throws Exception
     */
    public ResponseBaseVo changeAfterOrder(String order , int statue) throws Exception {

        QuestionOrderExample example = new QuestionOrderExample();
        QuestionOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        criteria.andQuesOrderEqualTo(order);

        QuestionOrder after = new QuestionOrder();
        after.setQuesStatue(statue);

        int result = questionOrderMapper.updateByExampleSelective(after, example);
        return ResponseVoUtil.getResponse(result , "操作成功" , "操作失败");
    }

}
