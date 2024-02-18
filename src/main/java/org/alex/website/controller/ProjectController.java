package org.alex.website.controller;

import lombok.extern.slf4j.Slf4j;
import org.alex.website.entity.Project;
import org.alex.website.entity.Role;
import org.alex.website.entity.User;
import org.alex.website.service.ProjectService;
import org.alex.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping("/saveProject/{userId}")
    public boolean saveProject(@RequestBody Project project,@PathVariable Long userId){
        log.info(project.toString());
        User user = userService.getById(userId);
        if (user.getUserRole() == Role.TEAMLEADER || user.getUserRole() == Role.PROFESSOR || user.getUserRole() == Role.MARKER) {
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
        Project project = projectService.getById(projectID);
        if (user.getUserRole() == Role.ADMINISTRATOR) {
            projectService.removeById(project);

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
    public boolean enrollProject(@RequestBody Project project, User user){
        log.info(project.toString(),"-----", user.toString());
        if (user.getUserRole() == Role.STUDENT || user.getUserRole() == Role.TEAMLEADER){
            if (project.getCurrentStudents() < project.getMaxStudents())
                project.setCurrentStudents(project.getCurrentStudents()+1);
            projectService.updateById(project);
            user.setProjectId(project.getId());
            userService.updateById(user);
            return true;
        }
        return false;
    }


}
