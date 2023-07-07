package com.github.Lisim2023.quotes.test.aspect;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.Lisim2023.quotes.combo.ComboHelper;
import com.github.Lisim2023.quotes.test.common.AjaxResult;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.List;


@Aspect
@Component
public class QuotesJSONAspect {

    @Resource
    private ComboHelper comboHelper;

    @AfterReturning(value = "com.github.Lisim2023.quotes.test.aspect.QuotesAspect.quote()", returning = "result")
    public void afterReturning(Object result){
        if (result instanceof AjaxResult){
            if (((AjaxResult<?>) result).isQuote()){
                long timeStart = System.currentTimeMillis();
                Object data = ((AjaxResult<?>) result).getData();
                if (data instanceof Page){
                    List<?> records = ((Page<?>) data).getRecords();
                    ((Page) data).setRecords(comboHelper.appendQuoteAndDict(records));
                }else {
                    ((AjaxResult) result).setData(comboHelper.appendQuoteAndDict(data));
                }
                long timeEnd = System.currentTimeMillis();
                System.out.println("注入引用数据(JSON) 耗时：" + (timeEnd - timeStart) + "毫秒");
            }
        }
    }

}
