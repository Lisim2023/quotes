package com.github.Lisim2023.quotes.test.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.github.Lisim2023.quotes.dict.Dict;

import com.github.Lisim2023.quotes.test.base.EntityLogic;
import com.github.Lisim2023.quotes.test.consts.DictCodes;
import com.github.Lisim2023.quotes.test.consts.TableNames;



import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(TableNames.DICT)
public class SysDict extends EntityLogic {
    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典特征码
     */
    private String code;

    /**
     * 描述
     */
    private String description;


    /**
     * 是否启用(0: 否 1:是)
     */
    @Dict(DictCodes.YN)
    private Integer enabled;
}
