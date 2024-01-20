package org.alex.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.entity.User;
import org.alex.website.mapper.UserMapper;
import org.alex.website.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Service
@RequestMapping("/student")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
