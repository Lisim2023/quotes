package io.github.Lisim2023.quotes.test.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Lisim2023.quotes.test.modules.system.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
* @author Lisim
* @description 针对表【sys_role】的数据库操作Mapper
* @createDate 2023-02-28 13:24:59
* @Entity io.github.Lisim2023.quotes.test.modules.system.entity.SysRole
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @SuppressWarnings("MybatisXMapperMethodInspection")
    List<Map<String, Object>> queryQuoteData(String table, String columns, String key, List<String> values);

}
