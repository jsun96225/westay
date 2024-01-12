package com.westay.live.modules.admin.service;

import com.westay.common.service.BaseService;
import com.westay.live.modules.admin.entity.RolePermission;

import java.util.List;

public interface RolePermissionService extends BaseService<RolePermission> {

    List<Long> getPermissionIdList(Long roleId);

    /**
     * 保存或修改
     * @param roleId      角色ID
     * @param menuIdList  菜单ID列表
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色id，删除角色菜单关系
     * @param roleIds 角色ids
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * 根据菜单id，删除角色菜单关系
     * @param permissionId 菜单id
     */
    void deleteByPermissionId(Long permissionId);
}
