package io.github.Lisim2023.quotes.test.modules.system.controller;



import io.github.lisim2023.quotes.combo.ComboHelper;

import io.github.Lisim2023.quotes.test.common.AjaxResult;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.Map;

/**
 * 字典助手测试
 */
@RestController
@RequestMapping("/sys/dictTest")
public class DictTestController {

    @Resource
    private ComboHelper comboHelper;


    @PostMapping("/cacheDict")
    public AjaxResult<?> cacheDict(@RequestBody Map<String, Map<String, Object>> dictData){
        comboHelper.cacheDictBatch(dictData);
        return AjaxResult.success();
    }

    @GetMapping("/cacheAllDict")
    public AjaxResult<?> cacheAllDict(){
        comboHelper.cacheAllDict();
        return AjaxResult.success();
    }

    @GetMapping("/rename")
    public AjaxResult<?> rename(@RequestParam String oldCode,
                                @RequestParam String newCode){
        comboHelper.rename(oldCode, newCode);
        return AjaxResult.success();
    }

    @GetMapping("/getDict")
    public AjaxResult<?> getDict(@RequestParam String dictCode,
                                 @RequestParam(name = "inverse", defaultValue = "false") boolean inverse){
        Map<String, Object> dict = comboHelper.getDict(dictCode, inverse);
        return AjaxResult.success(dict);
    }


    @GetMapping("/getDictBatch")
    public AjaxResult<?> getDictBatch(@RequestParam String dictCodes,
                                      @RequestParam(name = "inverse", defaultValue = "false") boolean inverse){
        Map<String, Map<String, Object>> dictBatch = comboHelper.getDictBatch(Arrays.asList(dictCodes.split(",")), inverse);
        return AjaxResult.success(dictBatch);
    }

    @GetMapping("/loadAllDict")
    public AjaxResult<?> loadAllDict(){
        Map<String, Map<String, Object>> map = comboHelper.loadAllDict();
        return AjaxResult.success(map);
    }

    @DeleteMapping("/removeDict")
    public AjaxResult<?> removeDict(@RequestParam String dictCode){
        Long aLong = comboHelper.removeDict(dictCode);
        return AjaxResult.success("成功移除 " + aLong + " 条字典数据!");
    }

    @DeleteMapping("/removeDictBatch")
    public AjaxResult<?> removeDictBatch(@RequestParam String dictCodes){
        Long aLong = comboHelper.removeDict(Arrays.asList(dictCodes.split(",")));
        return AjaxResult.success("成功移除 " + aLong + " 条字典数据!");
    }

    @DeleteMapping("/removeAllDict")
    public AjaxResult<?> removeAllDict(){
        Long aLong = comboHelper.removeAllDict();
        return AjaxResult.success("成功移除 " + aLong + " 条字典数据!");
    }


}
