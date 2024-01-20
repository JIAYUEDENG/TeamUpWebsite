package org.alex.website.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;


@Data
public class Student implements Serializable {

    private Long id;

    private String fullName;

    private String userName;

    private String password;

    @TableField("is_student")
    private Boolean isStudent;

    @TableField("is_team_leader")
    private Boolean isTeamLeader;

    private Enum program;


    private Boolean isActive;

}
