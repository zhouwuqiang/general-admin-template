package com.java.business.organization.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.*;
import com.java.business.utils.tree.dto.Tree;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 1:03
 */
public interface OrganizationFacade {

     /**
     * 组织列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(OrganizationTableRequestDto requestDto);

    /**
     * 保存组织
     * @param requestDto
     */
    void save(OrganizationSaveRequestDto requestDto);

    /**
     * 删除组织
     * @param requestDto
     */
    void delete(OrganizationSaveRequestDto requestDto);

    /**
     * 保存组织菜单关系
     * @param requestDto
     */
    void relationSave(RelationSaveRequestDto requestDto);

    /**
     * 查询组织结构树
     * @param requestDto
     * @return
     */
    List<Organization> queryListTree(OrganizationTreeRequestDto requestDto);
}
