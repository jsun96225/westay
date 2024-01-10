package com.westay.live.modules.log.service;


import com.westay.common.page.PageData;
import com.westay.common.service.BaseService;
import com.westay.live.modules.log.dto.SysLogErrorDTO;
import com.westay.live.modules.log.entity.ErrorLog;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogErrorService extends BaseService<ErrorLog> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(ErrorLog entity);

}