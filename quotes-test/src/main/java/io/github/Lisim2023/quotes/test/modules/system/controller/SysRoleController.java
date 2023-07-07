package io.github.Lisim2023.quotes.test.modules.system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.github.Lisim2023.quotes.test.modules.system.service.SysRoleService;
import io.github.Lisim2023.quotes.test.common.AjaxResult;
import io.github.Lisim2023.quotes.test.common.BaseController;
import io.github.Lisim2023.quotes.test.modules.system.entity.SysRole;
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
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController<SysRole, SysRoleService> {

    /**
     * 角色列表查询
     * @param sysRole
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<?> list(SysRole sysRole,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                           HttpServletRequest req){
        QueryWrapper<SysRole> queryWrapper = super.initQueryWrapper(sysRole, req);
        Page<SysRole> page = new Page<>(pageNo, pageSize);
        IPage<SysRole> pageList = service.page(page, queryWrapper);
        return AjaxResult.success(pageList);
    }

    //---------------------------------------------------------------------------------------------

    /**
     * 添加角色
     * @param sysRole
     */
    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody SysRole sysRole){
        service.save(sysRole);
        return AjaxResult.success("角色添加成功", sysRole);
    }

    /**
     * 修改角色
     * @param sysRole
     */
    @PutMapping("/edit")
    public AjaxResult<?> edit(@RequestBody SysRole sysRole){
        service.updateById(sysRole);
        return AjaxResult.success("角色修改成功");
    }

    /**
     * 通过ID查询角色
     * @param id
     */
    @GetMapping("/queryById")
    public AjaxResult<?> queryById(@RequestParam(name = "id") String id){
        SysRole sysRole = service.getById(id);
        return AjaxResult.success(sysRole);
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam(name = "id", required = true) String id){
        service.removeById(id);
        return AjaxResult.success("删除角色成功");
    }

    /**
     * 批量删除角色
     * @param ids
     */
    @DeleteMapping("/deleteBatch")
    public AjaxResult<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids){
        service.removeByIds(Arrays.asList(ids.split(",")));
        return AjaxResult.success("批量删除角色成功");
    }
}
