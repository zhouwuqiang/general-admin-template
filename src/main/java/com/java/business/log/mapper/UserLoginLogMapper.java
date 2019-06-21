package com.java.business.log.mapper;

import com.java.business.log.entity.UserLoginLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserLoginLogMapper extends Mapper<UserLoginLog> {
}
