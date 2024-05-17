package com.student.studentManagement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
