package io.github.Lisim2023.quotes.test.modules.system.service.impl;


import io.github.Lisim2023.quotes.test.common.AbstractQuoteService;
import io.github.Lisim2023.quotes.test.modules.system.service.UserGroupService;
import io.github.Lisim2023.quotes.test.modules.system.entity.UserGroup;
import io.github.Lisim2023.quotes.test.modules.system.mapper.UserGroupMapper;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceImpl extends AbstractQuoteService<UserGroupMapper, UserGroup> implements UserGroupService {
}
