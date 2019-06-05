package com.java.business.user.service;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:26
 */
public interface UserService {

    /**
     * 列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(UserTableRequestDto requestDto);

    /**
     * 保存用户
     * @param userBasicFace
     * @return
     */
    UserBasicFace save(UserBasicFace userBasicFace);
}
