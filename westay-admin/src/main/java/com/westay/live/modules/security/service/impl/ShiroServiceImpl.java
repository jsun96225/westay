
package com.westay.live.modules.security.service.impl;

import cn.hutool.core.util.StrUtil;
import com.westay.live.modules.security.dao.TokenDao;
import com.westay.live.modules.security.entity.Token;
import com.westay.live.modules.security.service.ShiroService;
import com.westay.live.modules.security.user.UserDetail;
import com.westay.live.modules.admin.dao.PermissionDao;
import com.westay.live.modules.admin.dao.UserDao;
import com.westay.live.modules.admin.entity.User;
import com.westay.live.modules.admin.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShiroServiceImpl implements ShiroService {
    private final PermissionDao permissionDao;
    private final UserDao sysUserDao;
    private final TokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {

        List<String> permissionsList;
        if (user.getVipUser() == UserStatusEnum.YES.value()) {
            permissionsList = permissionDao.getPermissionsList();
        } else {
            permissionsList = permissionDao.getUserPermissionsList(user.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String permissions : permissionsList) {
            if (StrUtil.isBlank(permissions)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public Token getByToken(String token) {
        return sysUserTokenDao.getByToken(token);
    }

    @Override
    public User getUser(Long userId) {
        return sysUserDao.selectById(userId);
    }

}