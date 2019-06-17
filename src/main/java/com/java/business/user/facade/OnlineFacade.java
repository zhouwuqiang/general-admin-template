package com.java.business.user.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserKickOutRequestDto;
import com.java.business.user.dto.UserOnlineTableRequestDto;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/14 18:20
 */
public interface OnlineFacade {


    /**
     * 查询在线用户
     * @param requestDto
     * @return
     */
    PageInfo onlineSessionTable(UserOnlineTableRequestDto requestDto);


    /**
     * 踢出用户
     * @param requestDto
     * @return
     */
    void kickOutUser(UserKickOutRequestDto requestDto);
}
