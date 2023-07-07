package com.github.Lisim2023.quotes.test.modules.system.service.impl;

import com.github.Lisim2023.quotes.test.common.AbstractQuoteService;
import com.github.Lisim2023.quotes.test.modules.system.mapper.SysUserMapper;
import com.github.Lisim2023.quotes.test.modules.system.service.SysUserService;
import com.github.Lisim2023.quotes.test.modules.system.entity.SysUser;

import org.springframework.stereotype.Service;

/**
* @author Lisim
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-02-28 13:20:51
*/
@Service
public class SysUserServiceImpl extends AbstractQuoteService<SysUserMapper, SysUser>
implements SysUserService {

}
