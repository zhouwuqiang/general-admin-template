package com.java.business.utils.tree.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/5 0:23
 */
@Getter
@Setter
@ToString
public class State implements Serializable {

    /**
     * 指示一个节点是否处于checked状态，用一个checkbox图标表示
     */
    private Boolean checked = false;
    /**
     * 指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     */
    private Boolean disabled = false;
    /**
     * 指示一个节点是否处于展开状态。
     */
    private Boolean expanded = false;
    /**
     * 指示一个节点是否可以被选择。
     */
    private Boolean selected = false;


}
