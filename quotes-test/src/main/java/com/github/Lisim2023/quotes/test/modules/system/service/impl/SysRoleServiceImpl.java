package com.github.Lisim2023.quotes.test.modules.system.service.impl;


import com.github.Lisim2023.quotes.quote.QuoteDataProvider;

import com.github.Lisim2023.quotes.test.common.AbstractQuoteService;
import com.github.Lisim2023.quotes.test.modules.system.mapper.SysRoleMapper;
import com.github.Lisim2023.quotes.test.modules.system.service.SysRoleService;
import com.github.Lisim2023.quotes.test.consts.TableNames;
import com.github.Lisim2023.quotes.test.consts.columns.DictColumns;
import com.github.Lisim2023.quotes.test.consts.columns.MenuColumns;
import com.github.Lisim2023.quotes.test.consts.columns.UserColumns;
import com.github.Lisim2023.quotes.test.modules.system.entity.SysRole;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Lisim
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-02-28 13:24:59
*/
@Service
public class SysRoleServiceImpl extends AbstractQuoteService<SysRoleMapper, SysRole>
        implements SysRoleService, QuoteDataProvider {

    @Override
    public List<Map<String, Object>> queryQuoteData(String table, String key, List<String> values) {
        String columns = String.join(",", TABLE_COLUMNS_FOR_QUOTE.get(table));
        return baseMapper.queryQuoteData(table, columns, key, values);
    }


    public static Map<String, String[]> TABLE_COLUMNS_FOR_QUOTE = new HashMap<>();

    static {
        TABLE_COLUMNS_FOR_QUOTE.put(TableNames.USER, new String[]{UserColumns.ID, UserColumns.USERNAME, UserColumns.NICKNAME, UserColumns.SEX, UserColumns.HOBBIES});

        TABLE_COLUMNS_FOR_QUOTE.put(TableNames.MENU, new String[]{MenuColumns.ID, MenuColumns.TITLE});

        TABLE_COLUMNS_FOR_QUOTE.put(TableNames.DICT, new String[]{DictColumns.ID, DictColumns.NAME});

//        TABLE_COLUMNS_FOR_QUOTE.put(TableNames.ROLE, new String[]{RoleColumns.ID, RoleColumns.NAME});
        TABLE_COLUMNS_FOR_QUOTE.put(TableNames.ROLE, new String[]{"*"});
    }

}
