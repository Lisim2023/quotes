package io.github.Lisim2023.quotes.test.base;

import com.baomidou.mybatisplus.annotation.TableField;

import io.github.lisim2023.quotes.base.AlsoQuote;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class TreeAbleLogic extends EntityLogic implements TreeAble{

    @TableField(exist = false)
    private String parentId;

    @AlsoQuote
    @TableField(exist = false)
    private List<?> children;
}
