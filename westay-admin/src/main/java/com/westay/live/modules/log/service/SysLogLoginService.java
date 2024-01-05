/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.westay.live.modules.log.service;

import com.westay.common.page.PageData;
import com.westay.common.service.BaseService;
import com.westay.live.modules.log.dto.SysLogLoginDTO;
import com.westay.live.modules.log.entity.LoginLog;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogLoginService extends BaseService<LoginLog> {

    PageData<SysLogLoginDTO> page(Map<String, Object> params);

    List<SysLogLoginDTO> list(Map<String, Object> params);

    void save(LoginLog entity);
}