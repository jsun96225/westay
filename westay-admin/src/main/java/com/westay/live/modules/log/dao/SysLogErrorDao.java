package com.westay.live.modules.log.dao;

import com.westay.common.dao.BaseDao;
import com.westay.live.modules.log.entity.ErrorLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 异常日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysLogErrorDao extends BaseDao<ErrorLog> {
	
}
