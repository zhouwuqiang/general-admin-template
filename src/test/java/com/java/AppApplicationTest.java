package com.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/17 10:58
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppApplicationTest {

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @Test
    public void demo() {
        System.out.println("测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}
