package com.github.Lisim2023.quotes.test.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.github.Lisim2023.quotes.base.AlsoQuote;
import com.github.Lisim2023.quotes.dict.Dict;
import com.github.Lisim2023.quotes.test.base.EntityLogic;
import com.github.Lisim2023.quotes.test.consts.DictCodes;
import com.github.Lisim2023.quotes.test.consts.TableNames;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;


/**
 * 字典示例
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(TableNames.USER)
//public class SysUser extends MapEntityLogic {
public class SysUser extends EntityLogic {

    private String username;

    private String nickname;

    private String realname;

    @Dict(DictCodes.SEX)
    private Integer sex;

    @Dict(DictCodes.HOBBIES)
    private String hobbies;

    private String password;

    private String avatar;

    private String email;

    private String address;


//    @TableField(exist = false)
//    private String sex_dictLabel;
//
//    @TableField(exist = false)
//    private String hobbies_dictLabel;
//
//    @TableField(exist = false)
//    private String createdBy_quote_nickname;



//    @AlsoQuote
//    @TableField(exist = false)
//    private List<List<SysUser>> insideTest1;
//
//    @AlsoQuote
//    @TableField(exist = false)
//    private List<Map<String, SysUser>> insideTest2;


}
