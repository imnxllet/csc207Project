package com.savvypro.csc207Project.ui;

import com.savvypro.csc207Project.controller.LoginController;
import com.savvypro.csc207Project.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Console UI to ask user for login input
 */
@Component
public class Login {
    @Autowired
    private LoginController loginController;
    public Student loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(" Enter user name => ");
        String userName = scanner.nextLine();

        System.out.print(" Enter password => ");
        String password = scanner.nextLine();

        Student loggedInStudent = loginController.login(userName, password);

        if (loggedInStudent != null) {
            System.out.println(String.format(" User %s successfully logged-in", loggedInStudent.getUsername()));
            return loggedInStudent;
        }

        System.out.println(" In valid userName of password ");

        return null;
    }
}
