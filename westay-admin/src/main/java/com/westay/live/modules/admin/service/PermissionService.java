package com.westay.live.modules.admin.service;

import com.westay.common.service.BaseService;
import com.westay.live.modules.admin.dto.PermissionDTO;
import com.westay.live.modules.admin.entity.Permission;
import com.westay.live.modules.security.user.UserDetail;
import java.util.List;

public interface PermissionService extends BaseService<Permission> {

    PermissionDTO get(Long id);

    void save(PermissionDTO dto);

    void update(PermissionDTO dto);

    void delete(Long id);


}
