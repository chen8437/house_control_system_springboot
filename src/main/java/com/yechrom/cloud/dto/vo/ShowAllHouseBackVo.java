package com.yechrom.cloud.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ShowAllHouseBackVo {

    private String buy_order;
    private Integer buy_statue;
    private Date buy_time;
    private String buy_uuid;
    private String buy_name;
    private String buy_address;
    private String buy_introduction;
    private Long buy_price;
    private Integer buy_pick;

}
