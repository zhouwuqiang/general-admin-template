package com.java.general.config.redis.service;

import java.util.List;
import java.util.Set;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/19 14:16
 */
public interface RedisService {

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(final String key, Object value);

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    boolean set(final String key, Object value, Long expireTime);

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    void remove(final String... keys);


    /**
     * 批量删除key
     *
     * @param pattern
     */
    void removePattern(final String pattern);

    /**
     * 删除对应的value
     *
     * @param key
     */
    void remove(final String key);

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    boolean exists(final String key);


    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    <T> T get(final String key);

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    void hmSet(String key, Object hashKey, Object value);

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    Object hmGet(String key, Object hashKey);



    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    void lPush(String k, Object v);


    /**
     * 列表获取
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    List<Object> lRange(String key, long start, long stop);


    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    void add(String key, Object value);

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    Set<Object> setMembers(String key);

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure  不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
     */
    void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    Set<Object> rangeByScore(String key, double scoure, double scoure1);


    /**
     * 锁定
     * @param owner
     * @param lockKey
     * @return
     */
    boolean lock(String owner,String lockKey);

    /**
     * 锁定
     * @param owner
     * @param lockKey
     * @param expiration 超时时间单位 秒
     * @return
     */
    boolean lock(String owner,String lockKey,Long expiration);

    /**
     * 锁定 线程等待
     * @param owner
     * @param lockKey
     * @return
     */
    boolean tryLock(String owner,String lockKey);

    /**
     * 锁定 线程等待
     * @param owner
     * @param lockKey
     * @param expiration 超时时间单位 秒
     * @return
     */
    boolean tryLock(String owner,String lockKey,Long expiration);

    /**
     * 解锁
     * @param owner
     * @param lockKey
     * @return
     */
    boolean unLock(String owner,String lockKey);


    /**
     * 续期
     * @param owner
     * @param lockKey
     * @param expiration
     * @return
     */
    boolean renewal(String owner,String lockKey, Long expiration);
}
