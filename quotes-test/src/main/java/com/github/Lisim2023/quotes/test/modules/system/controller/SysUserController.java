package com.github.Lisim2023.quotes.test.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.Lisim2023.quotes.test.modules.system.service.SysUserService;
import com.github.Lisim2023.quotes.test.common.AjaxResult;
import com.github.Lisim2023.quotes.test.common.BaseController;
import com.github.Lisim2023.quotes.test.modules.system.entity.SysUser;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lisim
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUser, SysUserService> {

    /**
     * 用户列表查询
     * @param sysUser
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<?> list(SysUser sysUser,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              HttpServletRequest req){
        QueryWrapper<SysUser> queryWrapper = super.initQueryWrapper(sysUser, req);
        Page<SysUser> page = new Page<>(pageNo, pageSize);
        IPage<SysUser> pageList = service.page(page, queryWrapper);
        return AjaxResult.quote(pageList);
    }


    @GetMapping("test1")
    public AjaxResult<?> test1(SysUser sysUser,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              HttpServletRequest req){
        QueryWrapper<SysUser> queryWrapper = super.initQueryWrapper(sysUser, req);
        Page<SysUser> page = new Page<>(pageNo, pageSize);
        IPage<SysUser> pageList = service.page(page, queryWrapper);
        Map<String, SysUser> userMap = pageList.getRecords().stream()
                .collect(Collectors.toMap(SysUser::getId, user -> user));
        return AjaxResult.quote(userMap);
    }

    @GetMapping("test2")
    public AjaxResult<?> test2(SysUser sysUser,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                 HttpServletRequest req){
        QueryWrapper<SysUser> queryWrapper = super.initQueryWrapper(sysUser, req);
        Page<SysUser> page = new Page<>(pageNo, pageSize);
        IPage<SysUser> pageList = service.page(page, queryWrapper);
        Map<String, SysUser> userMap = pageList.getRecords().stream()
                .collect(Collectors.toMap(SysUser::getId, user -> user));
        List<Map<String, SysUser>> test = new ArrayList<>();
        test.add(userMap);
        return AjaxResult.quote(test);
    }

    @GetMapping("test3")
    public AjaxResult<?> test3(SysUser sysUser,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                               HttpServletRequest req){
        QueryWrapper<SysUser> queryWrapper = super.initQueryWrapper(sysUser, req);
        Page<SysUser> page = new Page<>(pageNo, pageSize);
        IPage<SysUser> pageList = service.page(page, queryWrapper);
        List<SysUser> records = pageList.getRecords();
        Map<String, List<SysUser>> userMap = new HashMap<>();
        userMap.put("data", records);
        List<Map<String, List<SysUser>>> result = new ArrayList<>();
        result.add(userMap);
        return AjaxResult.quote(result);
    }


//    @GetMapping("insideTest1")
//    public AjaxResult<?> insideTest1(SysUser sysUser,
//                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
//                               HttpServletRequest req){
//        QueryWrapper<SysUser> queryWrapper = super.initQueryWrapper(sysUser, req);
//        Page<SysUser> page = new Page<>(pageNo, pageSize);
//        IPage<SysUser> pageList = service.page(page, queryWrapper);
//        List<SysUser> allUser = service.list();
//        List<SysUser> records = pageList.getRecords();
//        for (SysUser record : records) {
//            List<List<SysUser>> testData = new ArrayList<>();
//            for (int i = 0; i < records.size(); i++) {
//                testData.add(allUser);
//            }
//            record.setInsideTest1(testData);
//        }
//        return AjaxResult.quote(pageList);
//    }



//    @GetMapping("insideTest2")
//    public AjaxResult<?> insideTest2(SysUser sysUser,
//                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
//                                     HttpServletRequest req){
//        QueryWrapper<SysUser> queryWrapper = super.initQueryWrapper(sysUser, req);
//        Page<SysUser> page = new Page<>(pageNo, pageSize);
//        IPage<SysUser> pageList = service.page(page, queryWrapper);
//        List<SysUser> allUser = service.list();
//        Map<String, SysUser> allUserMap = allUser.stream()
//                .collect(Collectors.toMap(SysUser::getId, user -> user));
//        List<SysUser> records = pageList.getRecords();
//        for (SysUser record : records) {
//            List<Map<String, SysUser>> testData = new ArrayList<>();
//            for (int i = 0; i < records.size(); i++) {
//                testData.add(allUserMap);
//            }
//            record.setInsideTest2(testData);
//        }
//        return AjaxResult.quote(pageList);
//    }




    /**
     * 添加用户
     * @param sysUser
     */
    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody SysUser sysUser){
        service.save(sysUser);
        return AjaxResult.success("添加用户成功", sysUser);
    }

    /**
     * 修改用户
     * @param sysUser
     */
    @PutMapping("/edit")
    public AjaxResult<?> edit(@RequestBody SysUser sysUser){
        service.updateById(sysUser);
        return AjaxResult.success("修改用户成功");
    }


    /**
     * 通过ID查询用户
     * @param id
     */
    @GetMapping("/queryById")
    public AjaxResult<?> queryById(@RequestParam(name = "id") String id){
        SysUser sysUser = service.getById(id);
        return AjaxResult.success(sysUser);
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam(name = "id", required = true) String id){
        service.removeById(id);
        return AjaxResult.success("删除用户成功");
    }

    /**
     * 批量删除用户
     * @param ids
     */
    @DeleteMapping("/deleteBatch")
    public AjaxResult<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids){
        service.removeByIds(Arrays.asList(ids.split(",")));
        return AjaxResult.success("批量删除用户成功");
    }

}
