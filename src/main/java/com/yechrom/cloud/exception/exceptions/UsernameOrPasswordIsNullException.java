package com.yechrom.cloud.exception.exceptions;

/**
 * 用户名或密码错误
 */
public class UsernameOrPasswordIsNullException extends Exception{

    public UsernameOrPasswordIsNullException(String message){
        super(message);
    }
}
