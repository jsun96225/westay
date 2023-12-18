package com.westay.live.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("error_logs")
public class ErrorLog extends BaseEntity {
    private Long userId;
    private String errorMessage;
    private Date errorTime;

    // Getters and Setters
}
