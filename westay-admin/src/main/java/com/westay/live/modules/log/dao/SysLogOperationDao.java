package com.westay.live.modules.log.dao;

import com.westay.live.modules.log.entity.OperationLog;
import com.westay.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysLogOperationDao extends BaseDao<OperationLog> {
	
}
