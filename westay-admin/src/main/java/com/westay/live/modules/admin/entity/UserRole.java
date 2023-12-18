package com.westay.live.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_roles")
public class UserRole extends BaseEntity {
    private Long userId;
    private Long roleId;

    // Getters and Setters
}
