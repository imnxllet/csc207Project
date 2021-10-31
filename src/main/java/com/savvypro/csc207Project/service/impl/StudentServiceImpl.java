package com.savvypro.csc207Project.service.impl;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.repository.StudentRepository;
import com.savvypro.csc207Project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);

        return students;
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.findById(username).orElse(null);
    }

    @Override
    public void saveOrUpdate(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteByUsername(String username) {
        studentRepository.deleteById(username);
    }
}
