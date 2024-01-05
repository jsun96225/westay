package com.westay.live.modules.security.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * 登录用户信息
 */
@Data
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Integer gender;
    private Date createTime;
    private Date updateTime;
    private Integer points;
    private Integer vipUser; // 假设 0 表示非 VIP 用户，1 表示 VIP 用户
    private String profilePictureUrl;
    private Integer status; // 用户状态
    private Set<String> permissions;
    // 根据需要添加其他字段
}
