package com.java.business.organization.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Organization {

    private String text;

    /**
     * 是否有子菜单
     */
    private boolean hasChild = false;

    /**
     * 是否是顶级菜单
     */
    private boolean isRoot = false;
    /**
     * 编号
     */
    private String organizationCode;

    /**
     * 名称
     */
    private String organizationName;

    /**
     * 上级编号
     */
    private String parenCode;

    /**
     * 状态
     */
    private String organizationStatus;

    /**
     * 类型(00-部门,01-岗位)
     */
    private String organizationType;

    /**
     * 子对象
     */
    private List<Organization> nodes;

    /**
     * 添加子菜单
     *
     * @param menuList
     */
    public void addChild(List<Organization> menuList) {
        for (Organization item : menuList) {
            if (StringUtils.equals(item.getParenCode(), this.organizationCode)) {
                if (!this.hasChild) {
                    this.hasChild = true;
                    this.nodes = new ArrayList<>();
                }
                Organization organization = new Organization();
                BeanUtils.copyProperties(item, organization);
                this.nodes.add(organization);

            }
        }
        if (this.hasChild && nodes.size() > 0) {
            for (Organization child : nodes) {
                child.addChild(menuList);
            }
        }
    }
}
