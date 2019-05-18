package com.java.business.role.mapper;

import com.java.business.role.entity.RoleBasicFace;
import com.java.business.user.entity.UserBasicFace;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleBasicFaceMapper extends Mapper<RoleBasicFace> {


    /**
     * 查询用户角色集合
     * @param user
     * @return
     */
    List<RoleBasicFace> selectUserRole(UserBasicFace user);
}
