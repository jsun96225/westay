package com.westay.live.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("role_permissions")
public class RolePermission extends BaseEntity {
    private Long roleId;
    private Long permissionId;

    // Getters and Setters
}

