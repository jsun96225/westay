package com.westay.live.modules.admin.service.Impl;

import com.westay.common.dao.BaseDao;
import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.live.modules.admin.dao.PermissionDao;
import com.westay.live.modules.admin.dto.PermissionDTO;
import com.westay.live.modules.admin.entity.Permission;
import com.westay.live.modules.admin.service.PermissionService;
import com.westay.common.utils.ConvertUtils;
import com.westay.live.modules.admin.service.RolePermissionService;
import com.westay.live.modules.security.user.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDao, Permission> implements PermissionService {
    private final RolePermissionService rolePermissionService;

    @Override
    public PermissionDTO get(Long id) {
        Permission entity = baseDao.getById(id);
        PermissionDTO dto = ConvertUtils.sourceToTarget(entity, PermissionDTO.class);
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(PermissionDTO dto) {
        Permission entity = ConvertUtils.sourceToTarget(dto, Permission.class);

        //保存菜单
        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PermissionDTO dto) {
        Permission permission = ConvertUtils.sourceToTarget(dto, Permission.class);
        updateById(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deleteById(id);
        rolePermissionService.deleteByPermissionId(id);
    }

}
