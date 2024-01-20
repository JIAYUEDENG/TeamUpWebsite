package org.alex.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.alex.website.entity.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
}
