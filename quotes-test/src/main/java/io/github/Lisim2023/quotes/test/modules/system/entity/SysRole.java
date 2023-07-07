package io.github.Lisim2023.quotes.test.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.Lisim2023.quotes.test.base.EntityLogic;
import io.github.Lisim2023.quotes.test.consts.TableNames;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(TableNames.ROLE)
public class SysRole extends EntityLogic {

    private String name;

    private String code;

    private String remarks;

}
