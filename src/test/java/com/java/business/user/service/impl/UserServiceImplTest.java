package com.java.business.user.service.impl;

import com.java.AppApplicationTest;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.mapper.UserBasicFaceMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/17 11:12
 */
public class UserServiceImplTest extends AppApplicationTest {


    @Autowired
    private UserBasicFaceMapper userBasicFaceMapper;

    @Test
    public void queryTable() {
        List<UserBasicFace> userBasicFaceList = userBasicFaceMapper.selectAll();
        for (UserBasicFace u :userBasicFaceList) {
            System.out.println(u);
        }
    }

    @Test
    public void save() {
    }

    @Test
    public void deleteRoleRelation() {
    }

    @Test
    public void saveRoleRelation() {
    }

    @Test
    public void deleteOrganizationRelation() {
    }

    @Test
    public void saveOrganizationRelation() {
    }

    @Test
    public void queryBasicInfo() {
    }

    @Test
    public void queryRoleRelation() {
    }

    @Test
    public void queryOrganizationRelation() {
    }
}
