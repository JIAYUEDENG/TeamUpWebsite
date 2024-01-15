package org.alex.website;

import org.alex.website.mapper.StudentMapper;
import org.alex.website.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestMP{

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void testInsert(){
        Student student = new Student();
        student.setId(2L);
        student.setFullName("Lucy Rain");
        student.setPassword("456");
        student.setStudent(true);
        student.setTeamLeader(false);
        student.setUserName("Lucy2");
        studentMapper.insert(student);
    }
}
