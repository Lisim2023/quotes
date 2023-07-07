package io.github.Lisim2023.quotes.test.modules.system.controller;

import io.github.lisim2023.quotes.combo.ComboHelper;

import io.github.Lisim2023.quotes.test.common.AjaxResult;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 引用助手测试
 */
@RestController
@RequestMapping("/sys/quoteTest")
public class QuoteTestController {

    @Resource
    private ComboHelper comboHelper;


    @PostMapping("/cacheQuote")
    public AjaxResult<?> cacheQuote(@RequestParam String table,
                                    @RequestParam String key,
                                    @RequestBody Map<String, Object> quoteData){
        comboHelper.cacheQuote(table, key, quoteData);
        return AjaxResult.success();
    }

    @PostMapping("/cacheQuoteBatch")
    public AjaxResult<?> cacheQuoteBatch(@RequestParam String table,
                                         @RequestParam String key,
                                         @RequestBody List<Map<String, Object>> quotesData){
        comboHelper.cacheQuoteBatch(table, key, quotesData);
        return AjaxResult.success();
    }



    @GetMapping("/getQuote")
    public AjaxResult<?> getQuote(@RequestParam String table,
                                  @RequestParam String key,
                                  @RequestParam String value){
        Map<String, Object> quote = comboHelper.getQuote(table, key, value);
        return AjaxResult.success(quote);
    }

    @GetMapping("/getQuoteBatch")
    public AjaxResult<?> getQuoteBatch(@RequestParam String table,
                                       @RequestParam String key,
                                       @RequestParam String values){
        Map<String, Map<String, Object>> quoteMap = comboHelper.getQuoteBatch(table, key, Arrays.asList(values.split(",")));
        return AjaxResult.success(quoteMap);
    }


    @DeleteMapping("/removeQuote")
    public AjaxResult<?> removeQuote(@RequestParam String table,
                                              @RequestParam String key,
                                              @RequestParam String value){
        Long aLong = comboHelper.removeQuote(table, key, value);
        return AjaxResult.success("成功移除 " + aLong + " 条引用数据!");
    }

    @DeleteMapping("/removeQuoteBatch")
    public AjaxResult<?> removeQuoteBatch(@RequestParam String table,
                                                   @RequestParam String key,
                                                   @RequestParam String values){
        Long aLong = comboHelper.removeQuote(table, key, Arrays.asList(values.split(",")));
        return AjaxResult.success("成功移除 " + aLong + " 条引用数据!");
    }

}
