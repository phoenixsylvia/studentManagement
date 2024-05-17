package com.student.studentManagement.repository;

import com.student.studentManagement.model.Student;
import org.hibernate.type.descriptor.converter.internal.JpaAttributeConverterImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    Student findByEmail(String email);

    List<Student> findByEmailContaining(String email);
}
