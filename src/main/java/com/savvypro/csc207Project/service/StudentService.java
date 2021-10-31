package com.savvypro.csc207Project.service;

import com.savvypro.csc207Project.entity.Student;

import java.util.List;

public interface StudentService {
     List<Student> findAll();

     Student getStudentByUsername(String username);

     void saveOrUpdate(Student student);

     void deleteByUsername(String username);
}
