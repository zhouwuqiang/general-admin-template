package com.java.general.config.redis.service.impl;

import com.java.AppApplicationTest;
import com.java.general.config.redis.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;



/**
 * description :
 *
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/17 11:14
 */
public class RedisServiceImplTest extends AppApplicationTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void set() {
        boolean result = redisService.set("test", "test");
        Assert.assertTrue(result);
        redisService.remove("test");
    }

    @Test
    public void set1() throws InterruptedException {
        boolean result = redisService.set("testExpire", "demo", 2L);
        Assert.assertTrue(result);
        Thread.sleep(3000);
        Assert.assertFalse(redisService.exists("testExpire"));
    }

    @Test
    public void remove() {
        redisService.set("test1", "test1");
        redisService.set("test2", "test2");
        redisService.remove("test1", "test2");
        Assert.assertFalse(redisService.exists("test1"));
        Assert.assertFalse(redisService.exists("test2"));
    }

    @Test
    public void removePattern() {
        redisService.set("test11", "test11");
        redisService.set("test12", "test12");
        redisService.set("test21", "test21");
        redisService.set("test22", "test22");

        redisService.removePattern("test1?");
        Assert.assertFalse(redisService.exists("test11"));
        Assert.assertFalse(redisService.exists("test12"));

        redisService.removePattern("t*");
        Assert.assertFalse(redisService.exists("test21"));
        Assert.assertFalse(redisService.exists("test22"));
    }

    @Test
    public void remove1() {
        redisService.set("test", "test");
        redisService.remove("test");
    }

    @Test
    public void exists() {
        redisService.set("test", "test");
        Assert.assertTrue(redisService.exists("test"));
        redisService.remove("test");
    }

    @Test
    public void get() {
        redisService.set("test", "test");
        redisService.get("test");
        Assert.assertEquals(redisService.get("test"), "test");
    }

    @Test
    public void hmSet() {
        redisService.hmSet("test", "001", "001");
        redisService.hmSet("test", "002", "002");
        redisService.hmSet("test", "003", "003");

        redisService.remove("test");
        Assert.assertFalse(redisService.exists("test"));
    }

    @Test
    public void hmGet() {
        redisService.hmSet("test", "001", "001");
        Assert.assertEquals(redisService.hmGet("test", "001"), "001");
        redisService.remove("test");
        Assert.assertFalse(redisService.exists("test"));
    }

    @Test
    public void lPush() {
        redisService.lPush("test", "1");
        redisService.lPush("test", "2");
        redisService.lPush("test", "3");
        redisService.remove("test");
    }

    @Test
    public void lRange() {
        redisService.lPush("test", "1");
        redisService.lPush("test", "2");
        redisService.lPush("test", "3");
        List<Object> result = redisService.lRange("test", 1L, 2L);
        Assert.assertEquals(result.get(0), "2");
        Assert.assertEquals(result.get(1), "3");
        redisService.remove("test");
    }

    @Test
    public void add() {
        redisService.add("test", "1");
        redisService.add("test", "2");
        redisService.add("test", "3");
        Set<Object> result = redisService.setMembers("test");
        Assert.assertNotNull(result);
        redisService.remove("test");
    }

    @Test
    public void setMembers() {
        redisService.add("test", "1");
        redisService.add("test", "2");
        redisService.add("test", "3");
        Set<Object> result = redisService.setMembers("test");
        Assert.assertNotNull(result);
        redisService.remove("test");
    }

    @Test
    public void zAdd() {
        redisService.zAdd("test", "1",2);
        redisService.zAdd("test", "2",1);
        redisService.zAdd("test", "3",4);
        Set<Object> result = redisService.rangeByScore("test",0,5);
        Assert.assertNotNull(result);
        redisService.remove("test");
    }

    @Test
    public void rangeByScore() {
        redisService.zAdd("test", "1",2);
        redisService.zAdd("test", "2",1);
        redisService.zAdd("test", "3",4);
        Set<Object> result = redisService.rangeByScore("test",0,5);
        Assert.assertNotNull(result);
        redisService.remove("test");
    }
}
