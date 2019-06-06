package com.java.business.user.mapper;

import com.java.business.user.entity.UserRoleRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface UserRoleRelationMapper extends Mapper<UserRoleRelation> {
}
