package com.java.business.user.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserOnlineTableRequestDto;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.dto.UserkickOutRequestDto;
import com.java.business.user.facade.OnlineFacade;
import com.java.general.config.security.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<User> result = new ArrayList<>();

        List<Object> userList = sessionRegistry.getAllPrincipals();

        for (Object item : userList) {
            User user = (User) item;
            result.add(user);
        }

        pageInfo.setList(result);
        pageInfo.setTotal(result.size());
        return pageInfo;
    }

    @Override
    public User kickOutUser(UserkickOutRequestDto requestDto) {

        return null;
    }
}
