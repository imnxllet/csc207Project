package com.savvypro.csc207Project.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {
    @Autowired
    private StudentService studentService;

    public Student login(String username, String password) {
        Student user = studentService.getStudentByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }
}
