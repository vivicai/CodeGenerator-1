package com.zhengqing.demo.service.impl;

import com.zhengqing.demo.mapper.BaseMapper;
import com.zhengqing.demo.service.impl.BaseServiceImpl;
import com.zhengqing.demo.domain.User;
import com.zhengqing.demo.mapper.UserMapper;
import com.zhengqing.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    protected BaseMapper<User> getMapper() {
        return userMapper;
    }

}
