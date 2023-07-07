package com.github.Lisim2023.quotes.test.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.Lisim2023.quotes.dict.DictModel;
import com.github.Lisim2023.quotes.test.modules.system.entity.SysDict;

import java.util.List;


/**
* @author Lisim
* @description 针对表【sys_dict】的数据库操作Mapper
* @createDate 2023-02-28 13:33:45
* @Entity com.github.Lisim2023.quotes.test.modules.system.entity.SysDict
*/
public interface SysDictMapper extends BaseMapper<SysDict> {

    @SuppressWarnings("MybatisXMapperMethodInspection")
    List<DictModel> queryDictData(List<String> dictCodeList);

}
