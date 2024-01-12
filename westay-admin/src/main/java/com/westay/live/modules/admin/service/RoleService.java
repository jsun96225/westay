package com.westay.live.modules.admin.service;

import com.westay.common.service.BaseService;
import com.westay.live.modules.admin.dto.RoleDTO;
import com.westay.live.modules.admin.entity.Role;

public interface RoleService extends BaseService<Role> {

    RoleDTO get(Long id);

    void save(RoleDTO dto);

    void update(RoleDTO dto);

    void delete(Long[] ids);
}
