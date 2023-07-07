package com.github.Lisim2023.quotes.test.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.Lisim2023.quotes.test.modules.system.service.SysDictService;
import com.github.Lisim2023.quotes.test.common.AjaxResult;
import com.github.Lisim2023.quotes.test.common.BaseController;
import com.github.Lisim2023.quotes.test.modules.system.entity.SysDict;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lisim
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController<SysDict, SysDictService> {

    /**
     * 字典列表查询
     * @param sysDict
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<?> list(SysDict sysDict,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                           HttpServletRequest req){
        QueryWrapper<SysDict> queryWrapper = super.initQueryWrapper(sysDict, req);
        Page<SysDict> page = new Page<>(pageNo, pageSize);
        IPage<SysDict> pageList = service.page(page, queryWrapper);
        return AjaxResult.success(pageList);
    }

    /**
     * 添加字典
     * @param sysDict
     */
    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody SysDict sysDict){
        service.save(sysDict);
        return AjaxResult.success("添加字典成功");
    }

    /**
     * 修改字典
     * @param sysDict
     */
    @PutMapping("/edit")
    public AjaxResult<?> edit(@RequestBody SysDict sysDict){
        service.updateById(sysDict);
        return AjaxResult.success("修改字典成功");
    }

    /**
     * 通过ID查询字典
     * @param id
     */
    @GetMapping("/queryById")
    public AjaxResult<?> queryById(@RequestParam(name = "id") String id){
        SysDict sysDict = service.getById(id);
        return AjaxResult.success(sysDict);
    }

    /**
     * 删除字典
     * @param id
     */
    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam(name = "id") String id){
        service.removeById(id);
        return AjaxResult.success("删除字典成功");
    }

    /**
     * 批量删除字典
     * @param ids
     */
    @DeleteMapping("/deleteBatch")
    public AjaxResult<?> deleteBatch(@RequestParam(name = "ids") String ids){
        service.removeByIds(Arrays.asList(ids.split(",")));
        return AjaxResult.success("批量删除字典成功");
    }

}
