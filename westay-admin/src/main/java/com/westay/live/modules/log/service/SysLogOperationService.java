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
import com.westay.live.modules.log.dto.SysLogOperationDTO;
import com.westay.live.modules.log.entity.OperationLog;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogOperationService extends BaseService<OperationLog> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    void save(OperationLog entity);
}