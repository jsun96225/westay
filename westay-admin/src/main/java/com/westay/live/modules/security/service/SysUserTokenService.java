/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.westay.live.modules.security.service;

import com.westay.common.service.BaseService;
import com.westay.common.utils.Result;
import com.westay.live.modules.security.entity.Token;

/**
 * 用户Token
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends BaseService<Token> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Result createToken(Long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(Long userId);

}