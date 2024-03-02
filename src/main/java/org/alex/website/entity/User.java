package org.alex.website.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class User implements Serializable {
    private Long id;

    private Role userRole;

    private String fullName;

    private String userName;

    private String password;

    private Boolean isStudent;

    private Boolean isTeamLeader;

    private Boolean isProfessor;

    private Boolean isMarker;

    private Boolean isAdministrator;

    private Long projectId;

    public User(Long id, Role userRole, String fullName, String userName, String password) {
        this.id = id;
        this.userRole = userRole;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public User(Long id, Role userRole, String fullName, String userName, String password, Boolean isStudent, Boolean isTeamLeader, Boolean isProfessor, Boolean isMarker, Boolean isAdministrator, Long projectId) {
        this.id = id;
        this.userRole = userRole;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.isStudent = isStudent;
        this.isTeamLeader = isTeamLeader;
        this.isProfessor = isProfessor;
        this.isMarker = isMarker;
        this.isAdministrator = isAdministrator;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Role userRole() {
        return userRole;
    }

    public void userRole(Role role) {
        this.userRole = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }

    public Boolean getStudent() {
        return isStudent;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
    }

    public Boolean getTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(Boolean teamLeader) {
        isTeamLeader = teamLeader;
    }

    public Boolean getProfessor() {
        return isProfessor;
    }

    public void setProfessor(Boolean professor) {
        isProfessor = professor;
    }

    public Boolean getMarker() {
        return isMarker;
    }

    public void setMarker(Boolean marker) {
        isMarker = marker;
    }

    public Boolean getAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }

}
