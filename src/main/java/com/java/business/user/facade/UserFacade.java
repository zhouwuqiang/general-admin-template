package com.java.business.user.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.*;
import com.java.general.response.code.ResponseCode;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:22
 */
public interface UserFacade {


    /**
     * 列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(UserTableRequestDto requestDto);

    /**
     * 保存用户
     * @param requestDto
     */
    void save(UserSaveRequestDto requestDto);

    /**
     * 查询用户详情
     * @param requestDto
     * @return
     */
    UserDetailResponseDto detail(UserDetailRequestDto requestDto);

    /**
     * 更新用户登录密码
     * @param requestDto
     * @return
     */
    ResponseCode loginPassword(UserPasswordRequestDto requestDto);

    /**
     * 检查用户是否存在
     * @param requestDto
     * @return
     */
    ResponseCode checkExist(UserDetailRequestDto requestDto);
}
