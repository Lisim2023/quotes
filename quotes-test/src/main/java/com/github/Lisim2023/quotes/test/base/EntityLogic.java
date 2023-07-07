package com.github.Lisim2023.quotes.test.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.github.Lisim2023.quotes.quote.Quote;

import com.github.Lisim2023.quotes.test.consts.TableNames;
import com.github.Lisim2023.quotes.test.consts.columns.UserColumns;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class EntityLogic extends EntityBase {

    @Quote(table = TableNames.USER, columns = UserColumns.NICKNAME)
    private String createdBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Quote(table = TableNames.USER, columns = UserColumns.NICKNAME)
    private String updatedBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @TableLogic
    private Integer delFlag;
}
