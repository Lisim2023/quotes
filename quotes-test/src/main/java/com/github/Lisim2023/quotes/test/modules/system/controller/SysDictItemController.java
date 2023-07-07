package com.github.Lisim2023.quotes.test.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.Lisim2023.quotes.test.modules.system.service.SysDictItemService;
import com.github.Lisim2023.quotes.test.common.AjaxResult;
import com.github.Lisim2023.quotes.test.common.BaseController;
import com.github.Lisim2023.quotes.test.modules.system.entity.SysDictItem;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.util.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lisim
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/sys/dictItem")
public class SysDictItemController extends BaseController<SysDictItem, SysDictItemService> {

    /**
     * 字典项列表查询
     * @param sysDictItem
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<?> list(SysDictItem sysDictItem,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              HttpServletRequest req){
        QueryWrapper<SysDictItem> queryWrapper = super.initQueryWrapper(sysDictItem, req);
        Page<SysDictItem> page = new Page<>(pageNo, pageSize);
        IPage<SysDictItem> pageList = service.page(page, queryWrapper);
        return AjaxResult.success(pageList);
    }




    /**
     * 添加字典项
     * @param sysDictItem
     */
    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody SysDictItem sysDictItem){
        service.save(sysDictItem);
        return AjaxResult.success("添加字典项成功");
    }

    /**
     * 修改字典项
     * @param sysDictItem
     */
    @PutMapping("/edit")
    public AjaxResult<?> edit(@RequestBody SysDictItem sysDictItem){
        service.updateById(sysDictItem);
        return AjaxResult.success("修改字典项成功");
    }

    /**
     * 通过ID查询字典项
     * @param id
     */
    @GetMapping("/queryById")
    public AjaxResult<?> queryById(@RequestParam(name = "id") String id){
        SysDictItem sysDictItem = service.getById(id);
        return AjaxResult.success(sysDictItem);
    }

    /**
     * 删除字典项
     * @param id
     */
    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam(name = "id", required = true) String id){
        service.removeById(id);
        return AjaxResult.success("删除字典项成功");
    }

    /**
     * 批量删除字典项
     * @param ids
     */
    @DeleteMapping("/deleteBatch")
    public AjaxResult<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids){
        service.removeByIds(Arrays.asList(ids.split(",")));
        return AjaxResult.success("批量删除字典项成功");
    }
}
