package com.github.Lisim2023.quotes.test.base;

import com.github.Lisim2023.quotes.test.utils.ObjectUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 树形数据通用接口
 *
 * @author Lisim
 */
public interface TreeAble {

    String getId();
    void setId(String id);

    String getParentId();
    void setParentId(String parentId);

    List<?> getChildren();
    void setChildren(List<?> children);




    static <T extends TreeAble> List<T> buildTree(List<T> records){
        return buildTree(records, records).stream()
                .filter(record -> ObjectUtil.isEmpty(record.getParentId()))
                .collect(Collectors.toList());
    }

    static <T extends TreeAble> List<T> buildTree(List<T> parentList, List<T> subList){
        parentList.forEach(parent -> {;
            List<T> children = subList.stream()
                    .filter(sub -> parent.getId().equals(sub.getParentId()))
                    .collect(Collectors.toList());
            if (children.size() > 0){
                parent.setChildren(children);
            }
        });
        return parentList;
    }
}
