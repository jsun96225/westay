package com.westay.live.modules.admin.service.Impl;

import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.common.utils.ConvertUtils;
import com.westay.live.modules.admin.dao.RoleDao;
import com.westay.live.modules.admin.dto.RoleDTO;
import com.westay.live.modules.admin.entity.Role;
import com.westay.live.modules.admin.service.RolePermissionService;
import com.westay.live.modules.admin.service.RoleService;
import com.westay.live.modules.admin.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    private final RolePermissionService sysRolePermissionService;
    private final UserRoleService sysRoleUserService;
    @Override
    public RoleDTO get(Long id) {
        Role entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, RoleDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RoleDTO dto) {
        Role entity = ConvertUtils.sourceToTarget(dto, Role.class);

        //更新角色
        insert(entity);
        //更新角色菜单关系
        sysRolePermissionService.saveOrUpdate(entity.getId(), dto.getPermissionIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleDTO dto) {
        Role entity = ConvertUtils.sourceToTarget(dto, Role.class);

        //更新角色
        updateById(entity);
        //更新角色菜单关系
        sysRolePermissionService.saveOrUpdate(entity.getId(), dto.getPermissionIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除角色
        baseDao.deleteBatchIds(Arrays.asList(ids));

        //删除角色用户关系
        sysRoleUserService.deleteByRoleIds(ids);

        //删除角色菜单关系
        sysRolePermissionService.deleteByRoleIds(ids);
    }

}
