/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.westay.live.modules.log.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westay.common.constant.Constant;
import com.westay.common.page.PageData;
import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.common.utils.ConvertUtils;
import com.westay.live.modules.log.dao.SysLogLoginDao;
import com.westay.live.modules.log.dto.SysLogLoginDTO;
import com.westay.live.modules.log.entity.LoginLog;
import com.westay.live.modules.log.service.SysLogLoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, LoginLog> implements SysLogLoginService {

    @Override
    public PageData<SysLogLoginDTO> page(Map<String, Object> params) {
        IPage<LoginLog> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogLoginDTO.class);
    }

    @Override
    public List<SysLogLoginDTO> list(Map<String, Object> params) {
        List<LoginLog> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogLoginDTO.class);
    }

    private QueryWrapper<LoginLog> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");
        String creatorName = (String) params.get("creatorName");

        QueryWrapper<LoginLog> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(status), "status", status);
        wrapper.like(StrUtil.isNotBlank(creatorName), "creator_name", creatorName);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LoginLog entity) {
        insert(entity);
    }

}