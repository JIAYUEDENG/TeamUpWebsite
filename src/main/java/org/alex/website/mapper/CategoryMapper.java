package org.alex.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.alex.website.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
