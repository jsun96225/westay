package com.westay.live.modules.admin.dao;

import com.westay.common.dao.BaseDao;
import com.westay.live.modules.admin.entity.RolePermission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionDao extends BaseDao<RolePermission> {

    /**
     * 根据角色ID获取权限ID列表
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    List<Long> getPermissionIdList(Long roleId);

    /**
     * 根据角色ID数组删除角色权限关联
     * @param roleIds 角色ID数组
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * 根据权限ID删除角色权限关联
     * @param permissionId 权限ID
     */
    void deleteByPermissionId(Long permissionId);
}
