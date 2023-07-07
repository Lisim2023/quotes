package com.github.Lisim2023.quotes.test.common;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.Lisim2023.quotes.combo.ComboHelper;

import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 删、改数据时移除缓存
 * @param <M>
 * @param <T>
 */
public abstract class AbstractQuoteService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    @Resource
    @Lazy
    private ComboHelper comboHelper;


    @Override
    public boolean removeById(Serializable id) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        comboHelper.removeQuote(tableInfo.getTableName(), tableInfo.getKeyColumn(), id.toString());

        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        List<String> ids = idList.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        comboHelper.removeQuote(tableInfo.getTableName(), tableInfo.getKeyColumn(), ids);

        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(T entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
        comboHelper.removeQuote(tableInfo.getTableName(), tableInfo.getKeyColumn(), idVal.toString());

        return super.updateById(entity);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList) {
        try {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Map<String, Field> fieldMaps = ReflectionKit.getFieldMap(this.entityClass);
            Field field = fieldMaps.get(tableInfo.getKeyProperty());
            List<String> idVales = new ArrayList<>();
            field.setAccessible(true);
            for (T entity : entityList) {
                if (entity == null){
                    continue;
                }
                Object value = field.get(entity);
                if (value == null){
                    continue;
                }
                idVales.add(value.toString());
            }
            comboHelper.removeQuote(tableInfo.getTableName(), tableInfo.getKeyColumn(), idVales);
        }catch (ReflectiveOperationException e){
            throw ExceptionUtils.mpe("Error: Cannot read field in %s.  Cause:", e, this.entityClass.getSimpleName());
        }

        return super.updateBatchById(entityList);
    }
}
