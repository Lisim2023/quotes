package com.github.Lisim2023.quotes.test.modules.system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.Lisim2023.quotes.test.modules.system.service.SysMenuService;
import com.github.Lisim2023.quotes.test.common.AjaxResult;
import com.github.Lisim2023.quotes.test.common.BaseController;
import com.github.Lisim2023.quotes.test.base.TreeAble;
import com.github.Lisim2023.quotes.test.modules.system.entity.SysMenu;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lisim
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController<SysMenu, SysMenuService> {


    /**
     * 菜单树(不分页)
     * @return
     */
    @GetMapping("/tree")
    public AjaxResult<?> tree(){
        List<SysMenu> allMenus = service.list();
        return AjaxResult.quote(TreeAble.buildTree(allMenus));
    }

    /**
     * 菜单列表查询
     * @param
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<?> list(SysMenu sysMenu,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                           HttpServletRequest req){
        QueryWrapper<SysMenu> queryWrapper = super.initQueryWrapper(sysMenu, req);
        queryWrapper.lambda().orderByAsc(SysMenu::getOrderNum);
        Page<SysMenu> page = new Page<>(pageNo, pageSize);
        IPage<SysMenu> pageList = service.page(page, queryWrapper);
        return AjaxResult.success(pageList);
    }






    /**
     * 添加菜单
     * @param sysMenu
     */
    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody SysMenu sysMenu){
        service.save(sysMenu);
        return AjaxResult.success("菜单添加成功");
    }

    /**
     * 修改菜单
     * @param sysMenu
     */
    @PutMapping("/edit")
    public AjaxResult<?> edit(@RequestBody SysMenu sysMenu){
        service.updateById(sysMenu);
        return AjaxResult.success("菜单修改成功");
    }

    /**
     * 通过ID查询菜单
     * @param id
     */
    @GetMapping("/queryById")
    public AjaxResult<?> queryById(@RequestParam(name = "id") String id){
        SysMenu sysMenu = service.getById(id);
        return AjaxResult.success(sysMenu);
    }

    /**
     * 删除菜单
     * @param id
     */
    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam(name = "id") String id){
        service.removeById(id);
        return AjaxResult.success("删除菜单成功");
    }

    /**
     * 批量删除菜单
     * @param ids
     */
    @DeleteMapping("/deleteBatch")
    @Transactional
    public AjaxResult<?> deleteBatch(@RequestParam(name = "ids") String[] ids){
        service.removeByIds(Arrays.asList(ids));
        return AjaxResult.success("批量删除菜单成功!");
    }



}
