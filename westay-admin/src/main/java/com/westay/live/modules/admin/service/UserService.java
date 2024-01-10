package com.westay.live.modules.admin.service;

import com.westay.live.modules.admin.dto.UserDTO;
import com.westay.common.service.BaseService;
import com.westay.live.modules.admin.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User> {

    UserDTO getByUsername(String username);

    UserDTO get(Long id);

    void save(UserDTO dto);

    void update(UserDTO dto);

    void delete(Long[] ids);

    void updatePassword(Long id, String newPassword);

    void addPoints(Long id, int points);

    void subPoint(Long id, int points);

    int getPoints(Long id);
}
