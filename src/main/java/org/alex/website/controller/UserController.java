package org.alex.website.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.entity.User;
import org.alex.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(HttpServletRequest request, @RequestBody User user){
        String password = user.getPassword();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        User u = userService.getOne(queryWrapper);

        if (u == null || !u.getPassword().equals(password)){
            return null;
        }
        request.getSession().setAttribute("user", u.getId());
        return u;
    }

    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return true;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        if (user.getUserName() == null){
            user = new User(user.getId(),user.getUserRole(), user.getFullName(), user.getUserName(), user.getPassword());
            userService.save(user);
        }
        return user;
    }
}
