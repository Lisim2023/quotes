package com.github.Lisim2023.quotes.test.aspect;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.Lisim2023.quotes.combo.ComboHelper;
import com.github.Lisim2023.quotes.test.common.AjaxResult;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;

// 忽略了助手方法的返回值, JSON方式不能用这个
//@Aspect
//@Component
public class QuotesAspect {

    @Resource
    ComboHelper comboHelper;

    @Pointcut("execution(public * com.github.Lisim2023.quotes.test.modules..*.*Controller.*(..))")
    public void quote() {
    }

    @AfterReturning(value = "quote()", returning = "result")
    public void afterReturning(Object result){
        if (result instanceof AjaxResult) {
            if (((AjaxResult<?>) result).isQuote()) {
                long timeStart = System.currentTimeMillis();
                Object data = ((AjaxResult<?>) result).getData();
                if (data instanceof Page) {
                    comboHelper.appendQuoteAndDict(((Page<?>) data).getRecords());
                } else {
                    comboHelper.appendQuoteAndDict(data);
                }
                long timeEnd = System.currentTimeMillis();
                System.out.println("注入引用的数据 耗时：" + (timeEnd - timeStart) + "毫秒");
            }
        }
    }

}
