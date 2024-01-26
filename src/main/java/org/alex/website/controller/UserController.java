package org.alex.website.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.common.Result;
import org.alex.website.entity.User;
import org.alex.website.mapper.UserMapper;
import org.alex.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(HttpServletRequest request, @RequestBody User user){
        String password = user.getPassword();
        //password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        User u = userService.getOne(queryWrapper);

        if (u == null || !u.getPassword().equals(password)) return Result.error("Login failed");

        //if (!u.getActive()) return Result.error("Account is banned");

        request.getSession().setAttribute("user", u.getId());

        return Result.success(u);
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return Result.success("Logged out");
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user){
        if (user.getUserName() == null){
            user = new User();
            /*
            todo set new user attribute
             */
            userService.save(user);
            return Result.success(user);
        }
        return Result.error("User already exists");
    }
}
