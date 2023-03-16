package com.kob.backend1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend1.mapper.UserMapper;
import com.kob.backend1.pojo.User;
import com.kob.backend1.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null) {
             throw new RuntimeException("user doesn't exist");
        }
        return new UserDetailsImpl(user);
    }
}
