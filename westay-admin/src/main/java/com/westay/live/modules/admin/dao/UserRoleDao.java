package com.westay.live.modules.admin.dao;

import com.westay.common.dao.BaseDao;
import com.westay.live.modules.admin.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao extends BaseDao<UserRole> {

    /**
     * 根据用户ID获取角色ID列表
     * @param userId 用户ID
     * @return 角色ID列表
     */
    List<Long> getRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID删除用户角色关联
     * @param userId 用户ID
     */
    void deleteByUserId(@Param("userId") Long userId);



    /**
     * 根据角色ids，删除角色用户关系
     * @param roleIds 角色ids
     */
    void deleteByRoleIds(Long[] roleIds);
}
