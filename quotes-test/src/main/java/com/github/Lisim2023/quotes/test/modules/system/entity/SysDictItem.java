package com.github.Lisim2023.quotes.test.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.Lisim2023.quotes.dict.Dict;
import com.github.Lisim2023.quotes.quote.Quote;
import com.github.Lisim2023.quotes.test.base.EntityBase;
import com.github.Lisim2023.quotes.test.consts.DictCodes;
import com.github.Lisim2023.quotes.test.consts.TableNames;
import com.github.Lisim2023.quotes.test.consts.columns.DictColumns;



import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(TableNames.DICT_ITEM)
public class SysDictItem extends EntityBase {
    /**
     * 字典id
     */
    @Quote(table = TableNames.DICT, columns = DictColumns.NAME)
    private String dictId;

    /**
     * 字典特征码
     */
    private String dictCode;

    /**
     * 字典项文本
     */
    private String text;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否启用(0: 否 1:是)
     */
    @Dict(DictCodes.YN)
    private Integer enabled;

}
