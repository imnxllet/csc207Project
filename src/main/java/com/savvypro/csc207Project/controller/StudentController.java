package com.savvypro.csc207Project.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class StudentController {
    @Autowired
    private StudentService studentService;

    public void displayStudent(String username) {
        Student student = studentService.getStudentByUsername(username);

        String info = String.format("Username: %s \n" +
                "First Name: %s \n" +
                "Last Name: %s \n", student.getUsername(),
                student.getFirstName(), student.getLastName());
        System.out.println(info);
    }
    public boolean updateStudent(Student newStudent) {
        Student oldStudent = studentService.getStudentByUsername(newStudent.getUsername());

        if (oldStudent != null && StringUtils.isAlpha(newStudent.getFirstName())
                && StringUtils.isAlpha(newStudent.getLastName())) {
            oldStudent.setFirstName(newStudent.getFirstName());
            oldStudent.setLastName(newStudent.getLastName());

            studentService.saveOrUpdate(oldStudent);

            return true;
        }

        return false;
    }
}