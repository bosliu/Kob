package com.kob.backend1.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend1.mapper.UserMapper;
import com.kob.backend1.pojo.User;
import com.kob.backend1.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if (username == null) {
            map.put("error_message", "username can't be empty");
            return map;
        }
        if (password == null || confirmedPassword == null) {
            map.put("error_message", "password can't be empty");
            return map;
        }

        username = username.trim();
        if (username.length() == 0) {
            map.put("error_message", "username can't be empty");
            return map;
        }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error_message", "password can't be empty");
            return map;
        }

        if (username.length() > 100) {
            map.put("error_message", "The username length cannot be greater than 100");
            return map;
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "The password length cannot be greater than 100");
            return map;
        }

        if (!password.equals(confirmedPassword)) {
            map.put("error_message", "The passwords entered twice do not match");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "username already exists");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://avatars.githubusercontent.com/u/100297764?v=4";
        User user = new User(null, username, encodedPassword, photo);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}

