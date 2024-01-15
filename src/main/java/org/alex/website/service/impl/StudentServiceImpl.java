package org.alex.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.common.LoginObject;
import org.alex.website.mapper.StudentMapper;
import org.alex.website.domain.Student;
import org.alex.website.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Service
@RequestMapping("/student")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public LoginObject<Student> login(HttpServletRequest request, @RequestBody Student student){
        String password = student.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getUserName, student.getUserName());
        Student stu = studentService.getOne(queryWrapper);

        if (stu == null || !stu.getPassword().equals(password)) return LoginObject.error("Login failed");

        if (!stu.isActive()) return LoginObject.error("Account is banned");

        request.getSession().setAttribute("student", stu.getId());

        return LoginObject.success(stu);
    }

    @PostMapping("/logout")
    public LoginObject<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("student");
        return LoginObject.success("Logged out");
    }
}
