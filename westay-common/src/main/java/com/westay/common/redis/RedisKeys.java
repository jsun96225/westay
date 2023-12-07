package com.westay.common.redis;

/**
 * Redis 键名管理类
 * 用于生成和管理 Redis 中使用的键名
 */
public class RedisKeys {

    /**
     * 生成用户权限标识的键
     * @param userId 用户的唯一标识
     * @return 返回用户权限标识的键名
     */
    public static String getUserPermissionsKey(Long userId) {
        return "user:permissions:" + userId;
    }

    /**
     * 生成登录用户信息的键
     * @param id 用户的唯一标识
     * @return 返回登录用户信息的键名
     */
    public static String getSecurityUserKey(Long id) {
        return "user:security:" + id;
    }

    /**
     * 生成验证码的键
     * @param uuid 验证码的唯一标识符
     * @return 返回验证码的键名
     */
    public static String getCaptchaKey(String uuid) {
        return "captcha:" + uuid;
    }

    // 以下是为未来城市卡功能预留的键

    /**
     * 生成城市信息的键
     * @param cityId 城市的唯一标识
     * @return 返回城市信息的键名
     */
    public static String getCityInfoKey(String cityId) {
        return "city:info:" + cityId;
    }

    /**
     * 生成热门城市列表的键
     * @return 返回热门城市列表的键名
     */
    public static String getPopularCitiesKey() {
        return "city:popular";
    }

    // 您可以根据需要继续添加其他键的生成方法
}
