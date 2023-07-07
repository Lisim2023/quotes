package io.github.Lisim2023.quotes.test.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.Lisim2023.quotes.test.modules.system.service.SysRoleUserService;
import io.github.Lisim2023.quotes.test.common.AjaxResult;
import io.github.Lisim2023.quotes.test.common.BaseController;
import io.github.Lisim2023.quotes.test.modules.system.entity.SysRoleUser;
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
@RequestMapping("/sys/roleUser")
public class SysRoleUserController extends BaseController<SysRoleUser, SysRoleUserService> {


    /**
     * 角色用户列表查询
     * @param sysRoleUser
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<?> list(SysRoleUser sysRoleUser,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              HttpServletRequest req){
        QueryWrapper<SysRoleUser> dictQueryWrapper = super.initQueryWrapper(sysRoleUser, req);
        Page<SysRoleUser> page = new Page<>(pageNo, pageSize);
        IPage<SysRoleUser> pageList = service.page(page, dictQueryWrapper);
        return AjaxResult.quote(pageList);
    }

    /**
     * 添加角色用户
     * @param sysRoleUser
     */
    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody SysRoleUser sysRoleUser){
        service.save(sysRoleUser);
        return AjaxResult.success("添加角色用户成功");
    }

    /**
     * 修改角色用户
     * @param sysRoleUser
     */
    @PutMapping("/edit")
    public AjaxResult<?> edit(@RequestBody SysRoleUser sysRoleUser){
        service.updateById(sysRoleUser);
        return AjaxResult.success("修改角色用户成功");
    }

    /**
     * 通过ID查询角色用户
     * @param id
     */
    @GetMapping("/queryById")
    public AjaxResult<?> queryById(@RequestParam(name = "id") String id){
        SysRoleUser sysRoleUser = service.getById(id);
        return AjaxResult.success(sysRoleUser);
    }

    /**
     * 删除角色用户
     * @param id 角色用户编号
     * @return
     */
    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam(name = "id", required = true) String id){
        service.removeById(id);
        return AjaxResult.success("删除角色用户成功");
    }

    /**
     * 批量删除角色用户
     * @param ids 角色用户编号
     * @return
     */
    @DeleteMapping("/deleteBatch")
    public AjaxResult<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids){
        service.removeByIds(Arrays.asList(ids.split(",")));
        return AjaxResult.success("批量删除角色用户成功");
    }

}
