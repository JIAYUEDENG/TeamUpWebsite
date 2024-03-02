package org.alex.website.entity;

public enum Role {
    //0
    STUDENT("student"),
    //1
    TEAMLEADER("teamLeader"),
    //2
    PROFESSOR("professor"),
    //3
    MARKER("marker"),
    //4
    ADMINISTRATOR("administrator");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
