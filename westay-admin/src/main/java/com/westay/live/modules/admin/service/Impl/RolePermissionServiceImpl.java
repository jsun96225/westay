package com.westay.live.modules.admin.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.live.modules.admin.dao.RolePermissionDao;
import com.westay.live.modules.admin.entity.RolePermission;
import com.westay.live.modules.admin.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {
    @Override
    public List<Long> getPermissionIdList(Long roleId) {
        return baseDao.getPermissionIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> permissionIdList) {
        //先删除角色菜单关系
        deleteByRoleIds(new Long[]{roleId});

        //角色没有一个菜单权限的情况
        if(CollUtil.isEmpty(permissionIdList)){
            return ;
        }

        //保存角色菜单关系
        for(Long permissionId : permissionIdList){
            RolePermission sysRolePermissionEntity = new RolePermission();
            sysRolePermissionEntity.setPermissionId(permissionId);
            sysRolePermissionEntity.setRoleId(roleId);

            //保存
            insert(sysRolePermissionEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPermissionId(Long permissionId) {
        baseDao.deleteByPermissionId(permissionId);
    }
}
