package org.alex.website.entity;

import lombok.Data;

@Data
public class EnrollStatus {
    Long statusId;

    Long projectId;

    Long studentId;

    boolean status;

    public EnrollStatus(long projectId, long studentId, boolean status) {
        this.projectId = projectId;
        this.studentId = studentId;
        this.status = status;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
