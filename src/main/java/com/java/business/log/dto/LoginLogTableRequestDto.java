package com.java.business.log.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/24 18:27
 */
@Getter
@Setter
@ToString
public class LoginLogTableRequestDto extends PageRequestDto {

    private String userName;

    private Date loginTime;
}
