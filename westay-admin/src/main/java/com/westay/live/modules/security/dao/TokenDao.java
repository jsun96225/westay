/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.westay.live.modules.security.dao;

import com.westay.common.dao.BaseDao;
import com.westay.live.modules.security.entity.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户Token
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface TokenDao extends BaseDao<Token> {

    Token getByToken(String token);

    Token getByUserId(Long userId);

    void updateToken(@Param("userId") Long userId, @Param("token") String token);
}
