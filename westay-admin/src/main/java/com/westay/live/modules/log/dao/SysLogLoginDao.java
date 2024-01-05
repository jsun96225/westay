package com.westay.live.modules.log.dao;

import com.westay.live.modules.log.entity.LoginLog;
import com.westay.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<LoginLog> {
	
}
