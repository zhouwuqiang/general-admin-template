package com.java.business.user.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserKickOutRequestDto;
import com.java.business.user.dto.UserOnlineTableRequestDto;
import com.java.business.user.facade.OnlineFacade;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/14 18:19
 */
@RestController
@RequestMapping("/online")
public class OnlineController {


    @Autowired
    private OnlineFacade onlineFacade;

    /**
     * 在线用户session
     *
     * @return
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/online/table")
    public ResponseDto onlineSession(@RequestBody UserOnlineTableRequestDto requestDto) {

        PageInfo pageInfo  = onlineFacade.onlineSessionTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }

    /**
     * 在线用户session
     *
     * @return
     */
    @RequestMapping(value = "/kick")
    @ControllerRecorder(path = "/online/kick")
    public ResponseDto onlineKick(@RequestBody UserKickOutRequestDto requestDto) {

        onlineFacade.kickOutUser(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

}
