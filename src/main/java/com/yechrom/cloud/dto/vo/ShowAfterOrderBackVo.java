package com.yechrom.cloud.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ShowAfterOrderBackVo {
    private String after_order;
    private int after_statue;
    private Date after_time;
    private String after_seller;
    private String after_phone;
    private String after_introduction;
}
