package com.westay.live.modules.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tokens")
public class Token extends BaseEntity {
    private Long userId;
    private String token;
    private Date expireDate;

    // Getters and Setters
}

