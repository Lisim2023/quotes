package com.github.Lisim2023.quotes.test.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.Lisim2023.quotes.test.modules.system.service.UserGroupService;
import com.github.Lisim2023.quotes.test.common.AjaxResult;
import com.github.Lisim2023.quotes.test.common.BaseController;
import com.github.Lisim2023.quotes.test.modules.system.entity.UserGroup;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@RestController
@RequestMapping("/sys/userGroup")
public class UserGroupController extends BaseController<UserGroup, UserGroupService> {


    /**
     * 用户团队列表查询
     * @param userGroup
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<?> list(UserGroup userGroup,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              HttpServletRequest req){
        QueryWrapper<UserGroup> queryWrapper = super.initQueryWrapper(userGroup, req);
        Page<UserGroup> page = new Page<>(pageNo, pageSize);
        IPage<UserGroup> pageList = service.page(page, queryWrapper);
        return AjaxResult.quote(pageList);
    }

    /**
     * 添加用户团队
     * @param userGroup
     */
    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody UserGroup userGroup){
        service.save(userGroup);
        return AjaxResult.success("添加用户团队成功");
    }

    /**
     * 修改用户团队
     * @param userGroup
     */
    @PutMapping("/edit")
    public AjaxResult<?> edit(@RequestBody UserGroup userGroup){
        service.updateById(userGroup);
        return AjaxResult.success("修改用户团队成功");
    }

    /**
     * 通过ID查询用户团队
     * @param id
     */
    @GetMapping("/queryById")
    public AjaxResult<?> queryById(@RequestParam(name = "id") String id){
        UserGroup userGroup = service.getById(id);
        return AjaxResult.success(userGroup);
    }

    /**
     * 删除用户团队
     * @param id 用户团队编号
     * @return
     */
    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam(name = "id", required = true) String id){
        service.removeById(id);
        return AjaxResult.success("删除用户团队成功");
    }

    /**
     * 批量删除用户团队
     * @param ids 用户团队编号
     * @return
     */
    @DeleteMapping("/deleteBatch")
    public AjaxResult<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids){
        service.removeByIds(Arrays.asList(ids.split(",")));
        return AjaxResult.success("批量删除用户团队成功");
    }
}
