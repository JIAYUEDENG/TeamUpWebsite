package org.alex.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.alex.website.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
