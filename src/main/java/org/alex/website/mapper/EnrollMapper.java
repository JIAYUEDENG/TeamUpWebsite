package org.alex.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.alex.website.entity.EnrollStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollMapper extends BaseMapper<EnrollStatus> {
}
