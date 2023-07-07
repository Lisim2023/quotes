package com.github.Lisim2023.quotes.test.base;


import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class MapEntityLogic extends EntityLogic implements MapEntity {

    @TableField(exist = false)
    private Map<String, Object> dataMap = new HashMap<>();
}
