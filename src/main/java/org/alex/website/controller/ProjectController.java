package org.alex.website.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.common.Result;
import org.alex.website.entity.Project;
import org.alex.website.entity.Role;
import org.alex.website.entity.User;
import org.alex.website.service.CategoryService;
import org.alex.website.service.ProjectService;
import org.alex.website.service.UserService;
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
    private UserService userService;

    @PostMapping("/saveProject")
    public Result<String> saveProject(@RequestBody Project project, Long id){
        log.info(project.toString());
        User user = userService.getById(id);
        if (user.getUserRole() == Role.TEAMLEADER || user.getUserRole() == Role.PROFESSOR || user.getUserRole() == Role.MARKER) {
            projectService.save(project);

            return Result.success("Added new project");
        }
        return Result.error("Only a teamleader could add a project");
    }

    @GetMapping("/getProject")
    public Result<Project> getProject(Long id){
        Project p =projectService.getById(id);
        return Result.success(p);
    }

    @PutMapping("/updateProject")
    public Result<String> updateProject(@RequestBody Project project, Long id){
        log.info("update project：{}",project);
        User user = userService.getById(id);
        if (user.getUserRole() == Role.TEAMLEADER || user.getUserRole() == Role.PROFESSOR || user.getUserRole() == Role.MARKER) {
            projectService.updateById(project);
            return Result.success("update project success");
        }
        return Result.error("Only a teamleader could update a project");
    }

//    @GetMapping("/listProject")
//    public Result<List<Project>> listProject(Project project){
//        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(project.getCategoryId() != null ,Project::getCategoryId,project.getCategoryId());
//
//        queryWrapper.eq(Project::getStatus,1);
//
//        //queryWrapper.orderByAsc(Project::getSort).orderByDesc(Project::getUpdateTime);
//
//        List<Project> list = projectService.list(queryWrapper);
//
//        return Result.success(list);
//    }

    @PostMapping("/deleteProject")
    public Result<String> deleteProject(Long userID, Long projectID){
        //log.info(project.toString());
        User user = userService.getById(userID);
        Project project = projectService.getById(projectID);
        if (user.getUserRole() == Role.ADMINISTRATOR) {
            projectService.removeById(project);

            return Result.success("Deleted a project");
        }
        return Result.error("Only a Administrator could delete a project");
    }

    @PostMapping("/manageProject")
    public Result<String> manageProject(Long userID, Long projectID){
        //log.info(project.toString());
        User user = userService.getById(userID);
        Project project = projectService.getById(projectID);
        if (user.getUserRole() == Role.PROFESSOR || user.getUserRole() == Role.MARKER){
            project.setStatus(false);
        }
        return Result.error("Only a professor could edit the status of a project");
    }

    @PostMapping("/enrollProject")
    public Result<String> enrollProject(@RequestBody Project project, @RequestBody User user){
        log.info(project.toString(),"-----", user.toString());
        if (user.getUserRole() == Role.STUDENT || user.getUserRole() == Role.TEAMLEADER){

        }
    }


}
