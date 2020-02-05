package com.yechrom.cloud.dto.vo;

import lombok.Data;

/**
 * 更新用户信息 传入的
 */
@Data
public class UpdateUserVo {
    private String name;
    private String password;
    private String introduction;
    private String uuid;
}
