package io.github.Lisim2023.quotes.test.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import io.github.Lisim2023.quotes.test.base.TreeAbleLogic;
import io.github.Lisim2023.quotes.test.consts.DictCodes;
import io.github.Lisim2023.quotes.test.consts.TableNames;
import io.github.Lisim2023.quotes.test.consts.columns.MenuColumns;
import io.github.lisim2023.quotes.dict.Dict;
import io.github.lisim2023.quotes.quote.Quote;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 递归示例
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(TableNames.MENU)
public class SysMenu extends TreeAbleLogic {
    /**
     * 上级菜单编号，一级菜单为""
     */
    @Quote(table = TableNames.MENU, columns = MenuColumns.TITLE)
    private String parentId;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 组件
     */
    private String component;

    /**
     * 菜单类型  (0:目录 1:菜单 2:按钮)
     */
    @Dict(DictCodes.MENU_TYPE)
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 显示顺序
     */
    private Double orderNum;

    /**
     * 是否缓存 (0:不缓存 1:缓存)
     */
    @Dict(DictCodes.YN)
    private Integer isCache;


    /**
     * 菜单状态 (0:停用 1:启用)
     */
    private Integer status;



//    @TableField(exist = false)
//    private String parentId_quote_title;
//
//    @TableField(exist = false)
//    private String type_dictLabel;
//
//    @TableField(exist = false)
//    private String isCache_dictLabel;
//
//    @TableField(exist = false)
//    private String createdBy_quote_nickname;
}
