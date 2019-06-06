package com.java.business.user.mapper;

import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserBasicFaceMapper extends Mapper<UserBasicFace> {

    /**
     * 查询用户列表
     * @param requestDto
     * @return
     */
    List<UserBasicFace> selectUserList(UserTableRequestDto requestDto);
}
