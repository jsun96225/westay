package com.westay.live.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("guest_sessions")
public class GuestSession extends BaseEntity {
    private Date accessTime;
    private String ipAddress;
    private Integer cityViewsCount;

    // Getters and Setters
}
