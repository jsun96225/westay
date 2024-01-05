package com.westay.live.modules.admin.dao;

import com.westay.common.dao.BaseDao;
import com.westay.live.modules.admin.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionDao extends BaseDao<Permission> {

    /**
     * 根据权限ID获取权限实体
     * @param id 权限ID
     * @return 权限实体
     */
    Permission getById(@Param("id") Long id);

    /**
     * 获取指定用户的权限列表
     * @param userId 用户ID
     * @return 用户的权限名称列表
     */
    List<String> getUserPermissionsList(Long userId);

    /**
     * 获取所有权限列表
     * @return 所有权限名称列表
     */
    List<String> getPermissionsList();
}
