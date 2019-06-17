package com.java.business.user.facade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserOnlineTableRequestDto;
import com.java.business.user.dto.UserKickOutRequestDto;
import com.java.business.user.facade.OnlineFacade;
import com.java.general.config.security.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/14 18:20
 */
@Service
public class OnlineFacadeImpl implements OnlineFacade {

    @Autowired
    private SessionRegistry sessionRegistry;


    @Override
    public PageInfo onlineSessionTable(UserOnlineTableRequestDto requestDto) {
        PageInfo pageInfo = new PageInfo();

        List<JSONObject> result = new ArrayList<>();

        List<Object> userList = sessionRegistry.getAllPrincipals();

        for (Object item : userList) {
            List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(item, false);
            JSONObject user = (JSONObject) JSON.toJSON(item);
            user.put("loginSession",sessionInformationList.get(0).getSessionId());
            result.add(user);
        }


        pageInfo.setList(result);
        pageInfo.setTotal(result.size());
        return pageInfo;
    }

    @Override
    public void kickOutUser(UserKickOutRequestDto requestDto) {

        SessionInformation sessionInformation = sessionRegistry.getSessionInformation(requestDto.getSessionId());
        sessionInformation.expireNow();
    }
}
