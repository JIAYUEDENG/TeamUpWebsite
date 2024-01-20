package org.alex.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.mapper.StudentMapper;
import org.alex.website.entity.Student;
import org.alex.website.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Service
@RequestMapping("/student")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
