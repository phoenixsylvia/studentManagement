package com.student.studentManagement.controller;

import com.student.studentManagement.dto.CreateStudentRequest;
import com.student.studentManagement.dto.StudentResponse;
import com.student.studentManagement.dto.UpdateStudentRequest;
import com.student.studentManagement.model.Student;
import com.student.studentManagement.service.StudentService;
import com.student.studentManagement.service.impl.StudentServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class StudentController {

    @Autowired
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService){
        this.studentService = studentService;
    }


    private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
    @PostMapping("/addStudent")
    public StudentResponse addStudent(@Valid @RequestBody CreateStudentRequest request){
        StudentResponse response = studentService.addStudent(request);
        return response;
    }

    @GetMapping("/getStudent/{id}")
    @Cacheable(value="student", key="#id")
    public StudentResponse getStudent(@PathVariable Long id){
        StudentResponse response = studentService.getStudent(id);

        return response;
     }

     @PutMapping("/updateStudent/{id}")
     @CachePut(cacheNames = "student", key = "#id")
     public StudentResponse updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest updateStudentRequest){
        StudentResponse response = studentService.updateStudent(id, updateStudentRequest);
        return response;
     }

     @DeleteMapping("/deleteStudent/{id}")
     @CacheEvict(cacheNames = "student", key="#id", beforeInvocation = true)
     public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ("Student deleted successfully");
     }

     @GetMapping("/searchStudentByText/{email}")
     public List<Student> searchByText(@PathVariable String email){
       return studentService.searchByText(email);
     }
}
