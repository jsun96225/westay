package com.westay.live.modules.log.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.westay.common.constant.Constant;
import com.westay.common.page.PageData;
import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.common.utils.ConvertUtils;
import com.westay.live.modules.log.dao.SysLogOperationDao;
import com.westay.live.modules.log.dto.SysLogOperationDTO;
import com.westay.live.modules.log.entity.OperationLog;
import com.westay.live.modules.log.service.SysLogOperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogOperationServiceImpl extends BaseServiceImpl<SysLogOperationDao, OperationLog> implements SysLogOperationService {

    @Override
    public PageData<SysLogOperationDTO> page(Map<String, Object> params) {
        IPage<OperationLog> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogOperationDTO.class);
    }

    @Override
    public List<SysLogOperationDTO> list(Map<String, Object> params) {
        List<OperationLog> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogOperationDTO.class);
    }

    private QueryWrapper<OperationLog> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");

        QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(status), "status", status);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OperationLog entity) {
        insert(entity);
    }

}