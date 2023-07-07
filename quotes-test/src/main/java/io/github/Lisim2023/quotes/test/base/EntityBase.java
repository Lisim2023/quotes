package io.github.Lisim2023.quotes.test.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class EntityBase implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

}
