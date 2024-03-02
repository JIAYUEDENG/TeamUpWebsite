package org.alex.website.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.alex.website.entity.*;
import org.alex.website.service.EnrollService;
import org.alex.website.service.LeadingProjectsService;
import org.alex.website.service.ProjectService;
import org.alex.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private LeadingProjectsService leadingProjectsService;

    @PostMapping("/saveProject/{userId}")
    public boolean saveProject(@RequestBody Project project,@PathVariable Long userId){
        log.info(project.toString());
        User user = userService.getById(userId);
        if (user.getUserRole() == Role.TEAMLEADER) {
            project.setStatus(false);
            projectService.save(project);

            return true;
        }
        return false;
    }


    @GetMapping("/getProject/{id}")
    public Project getProject(@PathVariable Long id){
        return projectService.getById(id);
    }

    @PutMapping("/updateProject")
    public boolean updateProject(@RequestBody Project project, Long userId){
        log.info("update project：{}",project);
        User user = userService.getById(userId);
        if (user.getUserRole() == Role.TEAMLEADER || user.getUserRole() == Role.PROFESSOR || user.getUserRole() == Role.MARKER) {
            projectService.updateById(project);
            return true;
        }
        return false;
    }

    @DeleteMapping("/deleteProject")
    public boolean deleteProject(Long userID, Long projectID){
        //log.info(project.toString());
        User user = userService.getById(userID);
        if (user.getUserRole() == Role.ADMINISTRATOR) {
            projectService.removeById(projectID);
            return true;
        }
        return false;
    }

    @PostMapping("/manageProject")
    public boolean manageProject(Long userID, Long projectID){
        //log.info(project.toString());
        User user = userService.getById(userID);
        Project project = projectService.getById(projectID);
        if (user.getUserRole() == Role.PROFESSOR || user.getUserRole() == Role.MARKER){
            project.setStatus(false);
            projectService.updateById(project);
            return true;
        }
        return false;
    }

    @PostMapping("/enrollProject")
    public boolean enrollProject(@RequestBody Project project, @RequestBody User user){
        log.info(project.toString(),"-----", user.toString());
//        if (user.getUserRole() == Role.STUDENT){
//            if (project.getCurrentStudents() < project.getMaxStudents())
//                project.setCurrentStudents(project.getCurrentStudents()+1);
//            projectService.updateById(project);
//            user.setProjectId(project.getId());
//            userService.updateById(user);
//            return true;
//        }
        if (user.getUserRole() != Role.STUDENT) return false;

        EnrollStatus enrollStatus = new EnrollStatus(project.getId(), user.getId(), false);
        enrollService.save(enrollStatus);
        return true;
    }

    @GetMapping("reviewProject")
    public boolean reviewProject(User user, Project project){
        LambdaQueryWrapper<EnrollStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EnrollStatus::getStudentId, user.getId())
                    .eq(EnrollStatus::getProjectId, project.getId())
                    .eq(EnrollStatus::isStatus, false);
        EnrollStatus enrollStatus = new EnrollStatus(project.getId(), user.getId(), true);
        enrollService.update(enrollStatus, queryWrapper);
        return true;
    }

    @GetMapping("getAllProjects")
    public List<Project> getAllProjects(){
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select();
        return projectService.list(queryWrapper);
    }

    @GetMapping("getAllLeadedProject/{userId}")
    public List<Project> getAllLeadedProject(@PathVariable Long userId){
        LambdaQueryWrapper<LeadingProjects> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LeadingProjects::getLeaderId, userId);
        List<LeadingProjects> list = leadingProjectsService.list(queryWrapper);
        ArrayList<Long> arrayList = new ArrayList<>();
        for (LeadingProjects leadingProjects : list){
            arrayList.add(leadingProjects.getProjectId());
        }
        LambdaQueryWrapper<Project> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        for (Long projectId : arrayList){
            projectLambdaQueryWrapper.eq(Project::getId, projectId);
        }
        return projectService.list(projectLambdaQueryWrapper);
    }

    @GetMapping("getAllCandidates/{projectID}")
    public List<User> getAllCandidates(@PathVariable Long projectID){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getProjectId, projectID);
        return userService.list(queryWrapper);
    }

}
