package com.westay.live.modules.admin.dao;

import com.westay.common.dao.BaseDao;
import com.westay.live.modules.admin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao extends BaseDao<User> {

    List<User> getList(Map<String, Object> params);
    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户实体
     */
    User getByUsername(@Param("username") String username);

    /**
     * 根据用户ID获取用户信息
     * @param id 用户ID
     * @return 用户实体
     */
    User getById(@Param("id") Long id);

    /**
     * 更新用户密码
     * @param userId 用户ID
     * @param newPassword 新密码
     */
    int updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);

    /**
     * 根据角色ID查询用户总数
     * @param roleId 角色ID
     * @return 用户总数
     */
    int countByRoleId(@Param("roleId") Long roleId);

    /**
     * 增加用户积分
     * @param userId 用户ID
     * @param points 增加的积分数
     */
    void addPoints(@Param("userId") Long userId, @Param("points") int points);

    /**
     * 减少用户积分
     * @param userId 用户ID
     * @param points 减少的积分数
     */
    void subtractPoints(@Param("userId") Long userId, @Param("points") int points);

    /**
     * 更新用户积分
     * @param userId 用户ID
     * @param points 新的积分数
     */
    void updatePoints(@Param("userId") Long userId, @Param("points") int points);


}
