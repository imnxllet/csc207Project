package com.savvypro.csc207Project.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {
    private static Student DUMMY_FORM_PLACEHOLDER_STUDENT = new Student();

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public String getLoginPage(Model model) {
        model.addAttribute("student", DUMMY_FORM_PLACEHOLDER_STUDENT);

        return "login";
    }

    @PostMapping()
    public String login(@ModelAttribute(value="student") Student student, Model model) {

        Student user = studentService.getStudentByUsername(student.getUsername());

        if (user != null && student.getPassword().equals(user.getPassword())) {
            model.addAttribute("student", user);

            return "studentInfoPage";
        }

        model.addAttribute("student", DUMMY_FORM_PLACEHOLDER_STUDENT);
        model.addAttribute("message", "Username or password is wrong!");

        return "login";
    }
}