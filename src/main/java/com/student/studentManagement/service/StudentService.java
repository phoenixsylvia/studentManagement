package com.student.studentManagement.service;

import com.student.studentManagement.dto.CreateStudentRequest;
import com.student.studentManagement.dto.StudentResponse;
import com.student.studentManagement.dto.UpdateStudentRequest;
import com.student.studentManagement.model.Student;
import com.student.studentManagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    public StudentResponse addStudent(CreateStudentRequest request);

    public StudentResponse getStudent(Long id);

    public StudentResponse updateStudent(Long id, UpdateStudentRequest updateStudentRequest);

    public String deleteStudent(Long id);

    public List<Student> searchByText(String email);


}
