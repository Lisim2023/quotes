package io.github.Lisim2023.quotes.test.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.github.lisim2023.quotes.dict.DictModel;
import io.github.Lisim2023.quotes.test.modules.system.entity.SysDict;

import java.util.List;


/**
* @author Lisim
* @description 针对表【sys_dict】的数据库操作Mapper
* @createDate 2023-02-28 13:33:45
* @Entity io.github.Lisim2023.quotes.test.modules.system.entity.SysDict
*/
public interface SysDictMapper extends BaseMapper<SysDict> {

    @SuppressWarnings("MybatisXMapperMethodInspection")
    List<DictModel> queryDictData(List<String> dictCodeList);

}
