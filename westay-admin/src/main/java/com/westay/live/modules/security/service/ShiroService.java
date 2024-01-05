/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.westay.live.modules.security.service;

import com.westay.live.modules.security.entity.Token;
import com.westay.live.modules.security.user.UserDetail;
import com.westay.live.modules.admin.entity.User;

import java.util.List;
import java.util.Set;

/**
 * shiro相关接口
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(UserDetail user);

    Token getByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    User getUser(Long userId);

}
