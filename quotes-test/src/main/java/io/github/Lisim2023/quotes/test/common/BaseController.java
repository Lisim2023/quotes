package io.github.Lisim2023.quotes.test.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.service.IService;

import io.github.lisim2023.quotes.combo.ComboHelper;

import io.github.Lisim2023.quotes.test.base.EntityBase;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器基类
 * </p>
 *
 * @author Lisim
 * @since 2023-02-28
 */
public abstract class BaseController<T extends EntityBase, S extends IService<T>> {

    @Autowired(required = false)
    protected S service;

    @Resource
    @Lazy
    private ComboHelper comboHelper;


    protected QueryWrapper<T> initQueryWrapper(T entity, HttpServletRequest request){
        // 忽略request
        return initQueryWrapper(entity);
    }

    protected QueryWrapper<T> initQueryWrapper(T entity){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (entity == null){
            return queryWrapper;
        }

        TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        try {
            for (TableFieldInfo tableFieldInfo : fieldList) {
                Field field = tableFieldInfo.getField();
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value == null){
                    continue;
                }
                QueryCondition.addCondition(queryWrapper, tableFieldInfo.getColumn(), value.toString());
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

        return queryWrapper;
    }










    protected List<T> mapsToEntities(Collection<Map<String, Object>> beansData, Class<T> tClass)
            throws InvocationTargetException, IllegalAccessException, InstantiationException {

        List<T> entityList = new ArrayList<>();
        for (Map<String, Object> beanData : beansData) {
            entityList.add(mapToEntity(beanData, tClass));
        }
        return entityList;
    }

    protected T mapToEntity(Map<String, Object> beanData, Class<T> tClass)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {

        T t = tClass.newInstance();
        BeanUtils.populate(t, beanData);
        return t;
    }

}
