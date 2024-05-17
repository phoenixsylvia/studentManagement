package com.student.studentManagement.dto;

import com.student.studentManagement.model.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class StudentResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    public StudentResponse(){

    }
    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
    }
}
