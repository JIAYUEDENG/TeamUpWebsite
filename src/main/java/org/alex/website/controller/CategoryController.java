package org.alex.website.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.common.Result;
import org.alex.website.entity.Category;
import org.alex.website.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<String> save(@RequestBody Category category){
        log.info("category:{}", category);
        categoryService.save(category);
        return Result.success("Successfully added category");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        Page<Category> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    @DeleteMapping
    public Result<String> delete(Long id){
        log.info("delete category，id：{}",id);

        categoryService.removeById(id);

        return Result.success("Delete category success");
    }

    @PutMapping
    public Result<String> update(@RequestBody Category category){
        log.info("update category：{}",category);

        categoryService.updateById(category);

        return Result.success("update category success");
    }

}
