package com.java.business.organization.mapper;

import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.user.entity.UserBasicFace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrganizationBasicFaceMapper extends Mapper<OrganizationBasicFace> {

    /**
     * 查询用户组织
     * @param userBasicFace
     * @return
     */
    List<OrganizationBasicFace> selectUserOrganization(UserBasicFace userBasicFace);
}
