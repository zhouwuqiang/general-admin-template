package com.java.business.user.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.user.dto.UserTableRequestDto;

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
}
