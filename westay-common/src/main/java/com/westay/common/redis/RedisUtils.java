package com.westay.common.redis;

/**
 * RedisUtils 类方法功能对照表：
 *
 * 方法名                          | 功能描述
 * -------------------------------|---------------------------------------------------
 * set(String, Object, long)      | 将键值对存储到 Redis，并设置过期时间。
 * get(String, long)              | 从 Redis 获取键对应的值，并可选择性地更新过期时间。
 * delete(String)                 | 删除 Redis 中的一个或多个键。
 * hGet(String, String)           | 从哈希表中获取指定字段的值。
 * hGetAll(String)                | 获取哈希表中所有字段和值。
 * hMSet(String, Map, long)       | 向哈希表中批量添加字段和值，并设置过期时间。
 * hSet(String, String, Object, long) | 向哈希表中添加单个字段和值，并设置过期时间。
 * expire(String, long)           | 设置 Redis 键的过期时间。
 * hDel(String, Object...)        | 从哈希表中删除一个或多个字段。
 * leftPush(String, Object, long) | 将一个值插入到列表头部，并设置过期时间。
 * rightPop(String)               | 从列表尾部移除并获取一个值。
 */

/**
 * Redis工具类功能说明：
 *
 * set(String key, Object value, long expire):
 *     将键值对存储到 Redis，并设置过期时间。重载方法包括默认过期时间和自定义过期时间版本。
 *
 * get(String key, long expire):
 *     从 Redis 获取键对应的值，并可选择性地更新过期时间。重载方法包括带过期时间和不带过期时间版本。
 *
 * delete(String key):
 *     删除 Redis 中的一个或多个键。支持单个键和键的集合。
 *
 * hGet(String key, String field):
 *     从哈希表中获取指定字段的值。
 *
 * hGetAll(String key):
 *     获取哈希表中所有字段和值。
 *
 * hMSet(String key, Map<String, Object> map, long expire):
 *     向哈希表中批量添加字段和值，并设置过期时间。重载方法包括默认过期时间和自定义过期时间版本。
 *
 * hSet(String key, String field, Object value, long expire):
 *     向哈希表中添加单个字段和值，并设置过期时间。重载方法包括默认过期时间和自定义过期时间版本。
 *
 * expire(String key, long expire):
 *     设置 Redis 键的过期时间。时间单位为秒。
 *
 * hDel(String key, Object... fields):
 *     从哈希表中删除一个或多个字段。
 *
 * leftPush(String key, Object value, long expire):
 *     将一个值插入到列表头部，并设置过期时间。重载方法包括默认过期时间和自定义过期时间版本。
 *
 * rightPop(String key):
 *     从列表尾部移除并获取一个值。
 */

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**  默认过期时长为24小时，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;
    /**  过期时长为1小时，单位：秒 */
    public final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L;
    /**  过期时长为6小时，单位：秒 */
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6L;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1L;

    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public Object get(String key, long expire) {
        Object value = redisTemplate.opsForValue().get(key);
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
        return value;
    }

    public Object get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public Map<String, Object> hGetAll(String key) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    public void hMSet(String key, Map<String, Object> map) {
        hMSet(key, map, DEFAULT_EXPIRE);
    }

    public void hMSet(String key, Map<String, Object> map, long expire) {
        redisTemplate.opsForHash().putAll(key, map);

        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, DEFAULT_EXPIRE);
    }

    public void hSet(String key, String field, Object value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);

        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    public void leftPush(String key, Object value) {
        leftPush(key, value, DEFAULT_EXPIRE);
    }

    public void leftPush(String key, Object value, long expire) {
        redisTemplate.opsForList().leftPush(key, value);

        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }
}
