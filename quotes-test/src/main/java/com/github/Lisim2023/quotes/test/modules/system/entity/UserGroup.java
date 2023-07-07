package com.github.Lisim2023.quotes.test.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.Lisim2023.quotes.quote.Quote;
import com.github.Lisim2023.quotes.test.base.EntityLogic;
import com.github.Lisim2023.quotes.test.consts.TableNames;
import com.github.Lisim2023.quotes.test.consts.columns.UserColumns;



import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引用(多个值)示例
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(TableNames.USER_GROUP)
//public class UserGroup extends MapEntityLogic {
public class UserGroup extends EntityLogic {

    private String name;

    private String code;

    private String remarks;

    @Quote(table = TableNames.USER, columns = {UserColumns.USERNAME, UserColumns.NICKNAME})
    private String members;



//    @TableField(exist = false)
//    private String members_quote_username;
//
//    @TableField(exist = false)
//    private String members_quote_nickname;
//
//    @TableField(exist = false)
//    private String createdBy_quote_nickname;
}
