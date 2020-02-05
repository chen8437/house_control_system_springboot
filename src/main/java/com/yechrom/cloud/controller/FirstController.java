package com.yechrom.cloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.dto.pojo.User;
import com.yechrom.cloud.service.UserService;
import com.yechrom.cloud.util.RedisUtil;
import com.yechrom.cloud.webmagic.DemoFirst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import us.codecraft.webmagic.Spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;


@RestController
@Slf4j
public class FirstController {

    @Autowired
    RedisUtil redis;

    @Autowired
    UserService user;

    @RequestMapping("/first")
    public String test(){
//        Spider.create(new DemoFirst()).addUrl("https://github.com/code4craft").thread(5).run();
        List<User> users = user.getUsers();

        for (User user :
                users) {

            System.out.println(user.toString());
        }

        return "ok";
    }

    @RequestMapping("/info")
    public String test1(@RequestBody Map map){
        System.out.println(map);
        HashMap<String , Object> name1 = (HashMap)map.get("userinfo");
        String name = (String) name1.get("nickName");
        String action = (String) map.get("action");
        String things = (String) map.get("things");
        Long date = (Long) map.get("datetime");
        System.out.println(name + " 在" + date + "" + action + things );
        return null;
    }

    @RequestMapping("/redistest")
    public String test2(){

        redis.set("123123123" , "123213123");
        log.info(redis.get("123123123"));
        return null;
    }


//    @RequestMapping("/user/login")
//    @CrossOrigin(origins = "http://localhost:9527")
//    @ResponseBody
//    public String login(@RequestBody Map map){
//    //@RequestParam(value ="user") String user , @RequestParam(value ="password") String password
//
//        log.info(map.toString());
//
//        String[] roles = {"user"};
//        JSONObject response = new JSONObject();
//        response.put("errorcode" , 1);
//        JSONObject json = new JSONObject();
//        json.put("token","1111111111111111");
//
//
//
//        response.put("data" , json);
//
//        return response.toString();
//    }
//
//    @RequestMapping("/user/info")
//    @CrossOrigin(origins = "http://localhost:9527")
//    @ResponseBody
//    public String getinfo(@RequestParam(value = "token") String token){
//
//        log.info(token);
//
//        String[] roles = {"user"};
//
//        JSONObject response = new JSONObject();
//        response.put("errorcode" , 1);
//
//
//        JSONObject json = new JSONObject();
//        json.put("name","叶秋蓉");
//        json.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        json.put("token","1111111111111111");
//        json.put("roles",roles);
//        json.put("introduction","简介");
//
//
//        response.put("data" , json);
//
//        return response.toString();
//    }
//
//
//
//
//    @RequestMapping("/user/logout")
//    @CrossOrigin(origins = "http://localhost:9527")
//    @ResponseBody
//    public String logout(){
//
//
//        JSONObject response = new JSONObject();
//        response.put("errorcode" , 1);
//
//        response.put("data" , "logout success");
//
//        return response.toString();
//    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
    }


}
