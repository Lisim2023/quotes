package io.github.Lisim2023.quotes.test.modules.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.github.Lisim2023.quotes.test.modules.system.mapper.SysDictMapper;
import io.github.Lisim2023.quotes.test.modules.system.service.SysDictService;
import io.github.lisim2023.quotes.common.CommonUtil;
import io.github.lisim2023.quotes.dict.DictDataProvider;
import io.github.lisim2023.quotes.dict.DictModel;


import io.github.Lisim2023.quotes.test.modules.system.entity.SysDict;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Lisim
* @description 针对表【sys_dict】的数据库操作Service实现
* @createDate 2023-02-28 13:33:45
*/
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict>
        implements SysDictService, DictDataProvider {

    @Override
    public Map<String, Map<String, Object>> queryDictData(List<String> dictCodeList) {
        List<DictModel> dictModels = baseMapper.queryDictData(dictCodeList);
        return CommonUtil.arrangeDictModels(dictModels, dictCodeList);
    }

    @Override
    public Map<String, Map<String, Object>> queryAllDictData() {
        List<DictModel> dictModels = baseMapper.queryDictData(null);
        return CommonUtil.arrangeDictModels(dictModels, null);
    }

}
