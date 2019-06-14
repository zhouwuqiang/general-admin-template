package com.java.business.user.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
public class UserOnlineTableRequestDto extends PageRequestDto {

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户中文名
     */
    private String userLabel;

    /**
     * 组织机构编号
     */
    private String organizationCode;

    /**
     * 组织机构编号
     */
    private List<String> organizationCodeList;


    public interface Table{}

    public interface Insert{}

    public interface Update{}

    public interface Delete{}

    public interface Set{}
}
