package com.westay.live.modules.admin.service;

import com.westay.common.service.BaseService;
import com.westay.live.modules.admin.entity.UserRole;

import java.util.List;

public interface UserRoleService extends BaseService<UserRole> {


    /**
     * 保存或修改
     * @param userId      用户ID
     * @param roleIdList  角色ID列表
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据角色ids，删除角色用户关系
     * @param roleIds 角色ids
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * 根据用户id，删除角色用户关系
     * @param userIds 用户ids
     */
    void deleteByUserIds(Long[] userIds);

    /**
     * 角色ID列表
     * @param userId  用户ID
     */
    List<Long> getRoleIdList(Long userId);
}
