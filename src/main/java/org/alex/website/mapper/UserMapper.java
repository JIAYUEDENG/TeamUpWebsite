package org.alex.website.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.alex.website.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
