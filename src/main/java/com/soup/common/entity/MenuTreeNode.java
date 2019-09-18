package com.soup.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单树返回模型
 *
 * @author zhaoyi
 * @date 2018-05-12 21:05
 */
@Data
public class MenuTreeNode implements Serializable {

    /**
     * 菜单id
     */
    private Integer id;

    /**
     * 父级菜单id，顶级菜单的父级菜单id为0
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单是否打开
     */
    private boolean open = true;

    /**
     * 子级菜单列表
     */
    private List<MenuTreeNode> children;

    /**
     * 菜单icon图标
     */
    private String icon;

    /**
     * 是否选中
     */
    private boolean checked;

    public MenuTreeNode() {
    }

    public MenuTreeNode(Integer menuId, Integer parentId, String name) {
        this.id = menuId;
        this.parentId = parentId;
        this.name = name;
    }
}
