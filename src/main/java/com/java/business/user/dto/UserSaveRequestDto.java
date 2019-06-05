package com.java.business.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:24
 */
@Getter
@Setter
@ToString
public class UserSaveRequestDto{

    private UserBasicInfoRequestDto  basicInfo;

    private UserPowerInfoRequestDto powerInfo;
}
