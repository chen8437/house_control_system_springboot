package com.yechrom.cloud.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.yechrom.cloud.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class CheckTokenInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    RedisUtil redis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){

            //获取方法上的token
            HandlerMethod handerMethod = (HandlerMethod) handler;
            CheckToken checkToken = handerMethod.getMethod().getAnnotation(CheckToken.class);

            //获取类上的token
            if (checkToken == null) {
                checkToken =handerMethod.getMethod().getDeclaringClass().getAnnotation(CheckToken.class);
            }

            //如果方法和类上都没有token则直接放行
            if (checkToken == null) {
                return true;
            }

            //获取参数里面的token
//            String token = request.getParameter("token");
            String token = request.getHeader("House-Token");

            log.info("进入拦截器~ , 方法或类上的注解为: {} |请求的token为: {}" ,checkToken.toString() , token);

            if (!StringUtils.isNotBlank(token)){
                returnJson(response , "token不存在~");
                return false;
            }

            String result = redis.get(token);
            log.info("从redis获取的用户token为: {}" , result);
            if ("".equals(result)) {
                returnJson(response , "登录状态过期~");
                return false;
            }
            return true;
        }
        return false;
    }

    public void returnJson(HttpServletResponse response , String message){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {

            JSONObject json = new JSONObject();
            json.put("errorcode", -1);
            json.put("error", message);

            writer = response.getWriter();
            writer.print(json.toJSONString());
        } catch (IOException e) {
            log.error("拦截器返回json组装时发生IO异常~");
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
