package com.westay.live.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("login_logs")
public class LoginLog extends BaseEntity {
    private Long userId;
    private Date loginTime;
    private String ipAddress;

    // Getters and Setters
}
