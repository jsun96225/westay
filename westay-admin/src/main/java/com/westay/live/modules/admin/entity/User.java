package com.westay.live.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("users")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Integer gender;

    private String lastname;

    private String firstname;

    private String email;

    private String headUrl;

    private String phone;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer points;

    @TableField("vip_user")
    private Integer vipUser;

    @TableField("profile_picture_url")
    private String profilePictureUrl;

    private Integer status;

    // Getters and Setters
}
