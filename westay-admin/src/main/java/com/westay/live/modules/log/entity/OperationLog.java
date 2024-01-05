package com.westay.live.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("operation_logs")
public class OperationLog extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String operation;
    private String requestUri;
    private String requestMethod;
    private String requestParams;
    private Integer requestTime;
    private String userAgent;
    private String ip;
    private Integer status;
    private String creatorName;
    private Long creator;
    private Date createDate;

    // Getters and Setters
}
