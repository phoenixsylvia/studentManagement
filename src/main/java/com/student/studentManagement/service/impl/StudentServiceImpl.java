package com.student.studentManagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.student.studentManagement.dto.CreateStudentRequest;
import com.student.studentManagement.dto.StudentResponse;
import com.student.studentManagement.dto.UpdateStudentRequest;
import com.student.studentManagement.model.Student;
import com.student.studentManagement.repository.StudentRepository;
import com.student.studentManagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentResponse addStudent(CreateStudentRequest request) {
        Student foundStudent = studentRepository.findByEmail(request.getEmail());

        if(foundStudent!= null){
            throw new RuntimeException("Student already exists");
        }
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPassword(request.getPassword());

        Student savedStudent = studentRepository.save(student);

        StudentResponse response = new StudentResponse();
        response.setId(savedStudent.getId());
        response.setFirstName(savedStudent.getFirstName());
        response.setLastName(savedStudent.getLastName());
        response.setEmail(savedStudent.getEmail());

        return response;

    }

    public StudentResponse getStudent(Long id) {
        Student student;
        Optional<Student> findStudent = studentRepository.findById(id);
        if(findStudent.isPresent()){
            student = findStudent.get();
        }else{
            throw new RuntimeException("Student does not exist");
        }
        return new StudentResponse(student);

    }

    @Override
    public StudentResponse updateStudent(Long id, UpdateStudentRequest updateStudentRequest) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isEmpty()){
            throw new RuntimeException("Student does not exist");
        }

        Student student = optionalStudent.get();

        student.setFirstName(updateStudentRequest.getFirstName());
        student.setLastName(updateStudentRequest.getLastName());
        student.setEmail(updateStudentRequest.getEmail());

        Student updatedStudent = studentRepository.save(student);

        StudentResponse response = new StudentResponse();
        response.setId(updatedStudent.getId());
        response.setFirstName(updatedStudent.getFirstName());
        response.setLastName(updatedStudent.getLastName());
        response.setEmail(updatedStudent.getEmail());

        return response;
    }

    public String deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new RuntimeException("Student does not exist");
        }
        studentRepository.deleteById(id);
        return ("Student deleted successfully");

    }

    //FullText Indexing with MySQL
    public List<Student> searchByText(String email){
        return studentRepository.findByEmailContaining("gmail.com");
    }
}
