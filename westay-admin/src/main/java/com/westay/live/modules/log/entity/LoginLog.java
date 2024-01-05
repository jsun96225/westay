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
    private static final long serialVersionUID = 1L;
    private Integer operation;
    private Integer status;
    private String userAgent;
    private String ip;
    private String creatorName;
    private Long creator;
    private Date createDate;

    // Getters and Setters
}
