package com.savvypro.csc207Project.web.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ConditionalOnExpression("${web.enabled:false}")
@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute(value="student") Student student, Model model) {

        Student user = studentService.getStudentByUsername(student.getUsername());

        if (user != null && StringUtils.isAlpha(student.getFirstName())
                && StringUtils.isAlpha(student.getLastName())) {
            user.setFirstName(student.getFirstName());
            user.setLastName(student.getLastName());

            studentService.saveOrUpdate(user);

            model.addAttribute("student", student);

            return "studentInfoPage";
        }

        model.addAttribute("student", user);
        model.addAttribute("message", "update failed because name contains symbols or numbers!");

        return "studentInfoPage";
    }
}