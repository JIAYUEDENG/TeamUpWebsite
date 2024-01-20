package org.alex.website.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.common.Result;
import org.alex.website.entity.Project;
import org.alex.website.service.CategoryService;
import org.alex.website.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<String> save(@RequestBody Project project){
        log.info(project.toString());

        projectService.save(project);

        return Result.success("Added new project");
    }

    @GetMapping
    public Result<Project> get(@PathVariable Long id){
        Project p =projectService.getById(id);
        return Result.success(p);
    }

    @PutMapping
    public Result<String> update(@RequestBody Project project){
        log.info("update project：{}",project);
        projectService.updateById(project);
        return Result.success("update project success");
    }

    @GetMapping("/list")
    public Result<List<Project>> list(Project project){
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(project.getCategoryId() != null ,Project::getCategoryId,project.getCategoryId());

        queryWrapper.eq(Project::getStatus,1);

        //queryWrapper.orderByAsc(Project::getSort).orderByDesc(Project::getUpdateTime);

        List<Project> list = projectService.list(queryWrapper);

        return Result.success(list);
    }


}
