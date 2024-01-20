package org.alex.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.alex.website.entity.Category;
import org.alex.website.mapper.CategoryMapper;
import org.alex.website.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
