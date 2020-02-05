package com.yechrom.cloud.exception.exceptions;

/**
 * 未从redis中获取到用户info
 */
public class UserInfoHasNotExistException extends Exception{

    public UserInfoHasNotExistException(String message){
        super(message);
    }
}
