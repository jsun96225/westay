package com.westay.live.modules.admin.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.westay.common.service.impl.BaseServiceImpl;
import com.westay.common.utils.ConvertUtils;
import com.westay.live.modules.admin.dao.UserDao;
import com.westay.live.modules.admin.dto.UserDTO;
import com.westay.live.modules.admin.entity.User;
import com.westay.live.modules.admin.service.UserRoleService;
import com.westay.live.modules.admin.service.UserService;
import com.westay.live.modules.security.password.PasswordUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Arrays;
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    private final UserRoleService sysRoleUserService;


    @Override
    public UserDTO getByUsername(String username) {
        User entity = baseDao.getByUsername(username);
        return ConvertUtils.sourceToTarget(entity,UserDTO.class);

    }

    @Override
    public UserDTO get(Long id) {
        User entity = baseDao.getById(id);

        return ConvertUtils.sourceToTarget(entity, UserDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserDTO dto) {
        User entity = ConvertUtils.sourceToTarget(dto, User.class);
        String password = PasswordUtils.encode(entity.getPassword());
        entity.setPassword(password);
        insert(entity);
        sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDTO dto) {
        User entity = ConvertUtils.sourceToTarget(dto, User.class);
        //密码加密
        if (StrUtil.isBlank(dto.getPassword())) {
            entity.setPassword(null);
        } else {
            String password = PasswordUtils.encode(entity.getPassword());
            entity.setPassword(password);
        }
        //更新用户
        updateById(entity);
        //更新角色用户关系
        sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
    }

    @Override
    public void delete(Long[] ids) {
        //删除用户
        baseDao.deleteBatchIds(Arrays.asList(ids));

        //删除角色用户关系
        sysRoleUserService.deleteByUserIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        newPassword = PasswordUtils.encode(newPassword);

        baseDao.updatePassword(id, newPassword);
    }

    @Override
    public void addPoints(Long id, int points) {
        User user = baseDao.getById(id);
        if (user != null) {
            user.setPoints(user.getPoints() + points);
            int newPoint = user.getPoints() + points;
            baseDao.updatePoints(id, newPoint);
        }
    }

    @Override
    public void subPoint(Long id, int points) {
        User user = baseDao.getById(id);
        if (user != null && user.getPoints() >= points) {
            baseDao.subtractPoints(id, points);
        } else {
            // 抛出异常或进行其他处理，表示积分不足
            throw new RuntimeException("用户积分不足，无法完成操作");
        }
    }


    @Override
    public int getPoints(Long id) {
        User user = baseDao.getById(id);
        return user != null ? user.getPoints() : 0;
    }


}
