package org.alex.website.entity;

public enum Role {
    STUDENT("student"),
    TEAMLEADER("teamLeader"),
    PROFESSOR("professor"),
    MARKER("marker"),
    ADMINISTRATOR("administrator");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
