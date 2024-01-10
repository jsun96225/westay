package com.westay.live.modules.admin.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.live.modules.admin.dao.UserRoleDao;
import com.westay.live.modules.admin.entity.UserRole;
import com.westay.live.modules.admin.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除角色用户关系
        deleteByUserIds(new Long[]{userId});

        //用户没有一个角色权限的情况
        if(CollUtil.isEmpty(roleIdList)){
            return ;
        }

    //保存角色用户关系
        for(Long roleId : roleIdList){
            UserRole sysRoleUserEntity = new UserRole();
            sysRoleUserEntity.setUserId(userId);
            sysRoleUserEntity.setRoleId(roleId);
            //保存
            insert(sysRoleUserEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        baseDao.deleteByUserId(userIds);
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {

        return baseDao.getRoleIdsByUserId(userId);
    }
}
