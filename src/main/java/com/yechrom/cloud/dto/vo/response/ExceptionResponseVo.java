package com.yechrom.cloud.dto.vo.response;

import lombok.Data;

@Data
public class ExceptionResponseVo {

    private int errorcode;
    private Object error;
}
