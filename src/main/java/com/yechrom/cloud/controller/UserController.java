package com.yechrom.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.vo.*;
import com.yechrom.cloud.dto.vo.response.ResponseBaseVo;
import com.yechrom.cloud.dto.vo.response.ResponseErrorVo;
import com.yechrom.cloud.dto.vo.response.ResponseVo;
import com.yechrom.cloud.interceptor.CheckToken;
import com.yechrom.cloud.service.HouseService;
import com.yechrom.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户相关的controller
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    /**
     *
     * @param loginVo 登录接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/login")
    @ResponseBody
    public ResponseBaseVo login(@RequestBody LoginVo loginVo) throws Exception {

        log.info("调用登录接口 , 传入的参数为 : {}" , loginVo.toString());

        String token = userService.login(loginVo);

        //判断是否账号或者密码错误
        if (token.length() == 0){
            ResponseErrorVo response = new ResponseErrorVo();
            response.setErrorcode(0);
            response.setError("账号或密码错误~");
            log.info("账号或密码错误~");
            return response;
        }

        //成功时返回
        ResponseVo response = new ResponseVo();
        JSONObject result = new JSONObject();
        result.put("token" , token);
        response.setErrorcode(1);
        response.setData(result);
        return response;
    }


    /**
     *  获取用户信息
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping("/user/info")
    @ResponseBody
    @CheckToken
    public ResponseBaseVo getinfo(@RequestParam(value = "token") String token) throws Exception {

        log.info("用户调用获取信息接口 , 传入的参数为 token = {}" , token);

        JSONObject info = userService.getInfo(token);

        ResponseVo response = new ResponseVo();
        response.setErrorcode(1);
        response.setData(info);

        return response;
    }


    /**
     * 用户退出登录
     * @return
     */
    @RequestMapping("/user/logout")

    @ResponseBody
    public ResponseBaseVo logout(@RequestParam(value = "token") String token) throws Exception {

        log.info("用户调用登出接口 , 传入的token参数为 {} " , token);

        int result = userService.logout(token);

        ResponseBaseVo response ;

        if (result == 0){
            response = new ResponseErrorVo();
            response.setErrorcode(0);
            ((ResponseErrorVo) response).setError("删除失败~不存在该用户");
            return response;
        } else {
            response = new ResponseVo();
            response.setErrorcode(1);
            JSONObject jsonResult = new  JSONObject();
            jsonResult.put("msg" , "登出成功!");
            ((ResponseVo) response).setData(jsonResult);
            return response;
        }
    }


    /**
     * 获得所有的用户
     * @return
     */
    @GetMapping("/user/show")

    @ResponseBody
    @CheckToken
    public ResponseBaseVo showUsers(){

        log.info("调用了获取所有人员信息的接口.");

        List<ShowUserVo> users = userService.show();

        ResponseVo response = new ResponseVo();
        JSONObject data = new JSONObject();
        data.put("list" , users);
        data.put("total" , users.size());
        response.setErrorcode(1);
        response.setData(data);

        log.info("查看所有的用户接口 , 返回的报文为" + response.toString());
        return response;
    }


    @PostMapping("/user/update")
    @ResponseBody
    @CheckToken
    public ResponseBaseVo updateUser(@RequestBody UpdateUserVo requestVo) throws Exception {

        log.info("进入了更新用户信息的接口 , 传入的报文为: {}" , requestVo.toString());

        int result = userService.updateUser(requestVo);

        ResponseBaseVo response;

        if (result ==0){
            response = new ResponseErrorVo();
            response.setErrorcode(0);
            ((ResponseErrorVo) response).setError("更新用户信息失败~");
            return response;
        }
        response = new ResponseVo();
        response.setErrorcode(1);
        JSONObject data = new JSONObject();
        data.put("msg" , "更新用户信息成功~");
        ((ResponseVo) response).setData(data);
        return response;
    }

    @GetMapping("/user/delete")
    @ResponseBody
    @CheckToken
    public ResponseBaseVo deleteUser(@RequestParam String uuid ) throws Exception {

        log.info("进入了删除用户信息的接口 , 传入的报文为: {}" , uuid);

        int result = userService.deleteUser(uuid);

        ResponseBaseVo response;

        if (result ==0){
            response = new ResponseErrorVo();
            response.setErrorcode(0);
            ((ResponseErrorVo) response).setError("删除用户失败~");
            return response;
        }
        response = new ResponseVo();
        response.setErrorcode(1);
        JSONObject data = new JSONObject();
        data.put("msg" , "删除用户成功~");
        ((ResponseVo) response).setData(data);
        return response;
    }


    @PostMapping("/user/add")
    @ResponseBody
    @CheckToken
    public ResponseBaseVo addUser(@RequestBody AddUserVo userVo) throws Exception {

        log.info("进入了添加用户信息的接口 , 传入的报文为: {}" , userVo.toString());

        int result = userService.addUser(userVo);

        ResponseBaseVo response;

        if (result ==0){
            response = new ResponseErrorVo();
            response.setErrorcode(0);
            ((ResponseErrorVo) response).setError("添加用户信息失败~");
            return response;
        }
        response = new ResponseVo();
        response.setErrorcode(1);
        JSONObject data = new JSONObject();
        data.put("msg" , "添加用户信息成功~");
        ((ResponseVo) response).setData(data);
        return response;
    }


}
