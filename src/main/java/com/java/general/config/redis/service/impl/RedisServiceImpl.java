package com.java.general.config.redis.service.impl;

import com.java.general.config.redis.service.RedisService;
import com.java.general.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/19 14:16
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {


    /**
     * 默认超时时间 单位秒
     */
    private static final Long DEFAULT_EXPIRATION_SECOND = 30L;

    /**
     * 线程等待休眠时间
     */
    private static final Long DEFAULT_SLEEP_MILLIS = 500L;


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    @Override
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public <T> T get(final String key) {
        ValueOperations<Serializable, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    @Override
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    @Override
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    @Override
    public List<Object> lRange(String key, long start, long stop) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(key, start, stop);
    }

    @Override
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    @Override
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }


    @Override
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    @Override
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    @Override
    public boolean lock(String owner, String lockKey) {
        return lock(owner, lockKey, DEFAULT_EXPIRATION_SECOND);
    }

    @Override
    public boolean lock(String owner, String lockKey, Long expiration) {

        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {

            Boolean acquire = connection.setNX(lockKey.getBytes(), owner.getBytes());
            if (acquire) {
                connection.setEx(lockKey.getBytes(), expiration, owner.getBytes());
                return true;
            } else {
                return false;
            }

        });

    }

    @Override
    public boolean tryLock(String owner, String lockKey) {
        return tryLock(owner, lockKey, DEFAULT_EXPIRATION_SECOND);
    }

    @Override
    public boolean tryLock(String owner, String lockKey, Long expiration) {
        int count = 0;
        while (true) {

            if (lock(owner, lockKey, expiration)) {
                return true;
            }

            if (count > 30) {
                log.info("redis 获取锁等待超时,所有者:{},锁定key:{}", owner, lockKey);
                return false;
            }
            count++;

            sleepByMillis(DEFAULT_SLEEP_MILLIS);
        }
    }

    @Override
    public boolean unLock(String owner, String lockKey) {
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            String lockOwner = new String(connection.get(lockKey.getBytes()));
            if (StringUtils.equals(owner,lockOwner)) {
                connection.del(lockKey.getBytes());
                return true;
            } else {
                return false;
            }
        });
    }

    @Override
    public boolean renewal(String owner, String lockKey, Long expiration) {
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            String lockOwner = new String(connection.get(lockKey.getBytes()));
            if (StringUtils.equals(owner,lockOwner)) {
                connection.setEx(lockKey.getBytes(), expiration, owner.getBytes());
                return true;
            } else {
                return false;
            }
        });
    }


    /**
     * 线程休眠
     *
     * @param millis
     */
    private void sleepByMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("线程休眠异常!", e);
            throw new BusinessException("获取锁等待失败!{}", e.getMessage());
        }
    }

}
