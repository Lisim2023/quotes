package io.github.Lisim2023.quotes.test.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.github.Lisim2023.quotes.test.base.EntityBase;
import io.github.Lisim2023.quotes.test.consts.DictCodes;
import io.github.Lisim2023.quotes.test.consts.TableNames;
import io.github.Lisim2023.quotes.test.consts.columns.RoleColumns;
import io.github.Lisim2023.quotes.test.consts.columns.UserColumns;
import io.github.lisim2023.quotes.dict.Dict;
import io.github.lisim2023.quotes.quote.Quote;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引用(单个 多个字段)示例
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(TableNames.ROLE_USER)
public class SysRoleUser extends EntityBase {

    /**
     * 角色编号
     */
    @Quote(table = TableNames.ROLE, columns = RoleColumns.NAME)
    private String roleId;

    /**
     * 用户编号
     */
    @Quote(table = TableNames.USER, columns = {UserColumns.NICKNAME, UserColumns.USERNAME, UserColumns.SEX, UserColumns.HOBBIES})
    private String userId;

    @TableField(exist = false)
    @Dict(DictCodes.SEX)
    private String userId_quote_sex;

    @TableField(exist = false)
    @Dict(DictCodes.HOBBIES)
    private String userId_quote_hobbies;



//    @TableField(exist = false)
//    private String userId_quote_nickname;
//
//    @TableField(exist = false)
//    private String userId_quote_username;
//
//    @TableField(exist = false)
//    private String userId_quote_sex_dictLabel;
//
//    @TableField(exist = false)
//    private String userId_quote_hobbies_dictLabel;

}
