package com.java.business.utils.tree.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
public class Tree {

    /**
     * (必选)列表树节点上的文本。
     */
    private String code;


    /**
     * (必选)列表树节点上的文本。
     */
    private String text;

    /**
     * 列表树节点上的图标。
     */
    private String icon;

    /**
     * 当某个节点被选择后显示的图标。
     */
    private String selectedIcon;

    /**
     * 结合全局enableLinks选项为列表树节点指定URL。
     */
    private String href;

    /**
     * 指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
     */
    private String selectable;

    /**
     * 一个节点的初始状态。
     */
    private State state = new State();

    /**
     * 节点的前景色，覆盖全局的前景色选项。
     */
    private String color;

    /**
     * 节点的背景色，覆盖全局的背景色选项。
     */
    private String backColor;

    /**
     * 通过结合全局showTags选项来在列表树节点的右边添加额外的信息。
     */
    private List<String> tags = new ArrayList<>();

    /**
     * 子节点
     */
    private List<Tree> nodes;


    public void addTags(String tag) {
        this.tags.add(tag);
    }

    public void setChecked() {
        this.state.setChecked(true);
    }
}
