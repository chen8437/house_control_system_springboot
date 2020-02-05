package com.yechrom.cloud.util;

import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.dto.vo.response.ResponseErrorVo;
import com.yechrom.cloud.dto.vo.response.ResponseVo;

public class ResponseVoUtil {

    /**
     * 封装返回值
     * @param result
     * @return
     */
    public static ResponseBaseVo getResponse(int result , String success , String fail){
        ResponseBaseVo response ;
        if(result == 0){
            response = new ResponseErrorVo();
            response.setErrorcode(0);
            ((ResponseErrorVo) response).setError(fail);
            return response;
        }

        response = new ResponseVo();
        response.setErrorcode(1);
        JSONObject data = new JSONObject();
        data.put("msg" , success);
        ((ResponseVo) response).setData(data);
        return response;
    }
}
