package com.yechrom.cloud.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ShowAllHouseBackSellVo {

    private String sell_order;
    private Integer sell_statue;
    private Date sell_time;
    private String sell_uuid;
    private String sell_address;
    private String sell_name;
    private String sell_introduction;
    private Long sell_price;
    private Integer sell_pick;

}
