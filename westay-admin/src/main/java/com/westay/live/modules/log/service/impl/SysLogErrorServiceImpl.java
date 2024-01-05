/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.westay.live.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westay.common.constant.Constant;
import com.westay.common.page.PageData;
import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.common.utils.ConvertUtils;
import com.westay.live.modules.log.dao.SysLogErrorDao;
import com.westay.live.modules.log.dto.SysLogErrorDTO;
import com.westay.live.modules.log.entity.ErrorLog;
import com.westay.live.modules.log.service.SysLogErrorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogErrorServiceImpl extends BaseServiceImpl<SysLogErrorDao, ErrorLog> implements SysLogErrorService {

    @Override
    public PageData<SysLogErrorDTO> page(Map<String, Object> params) {
        IPage<ErrorLog> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogErrorDTO.class);
    }

    @Override
    public List<SysLogErrorDTO> list(Map<String, Object> params) {
        List<ErrorLog> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogErrorDTO.class);
    }

    private QueryWrapper<ErrorLog> getWrapper(Map<String, Object> params){
        QueryWrapper<ErrorLog> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ErrorLog entity) {
        insert(entity);
    }

}