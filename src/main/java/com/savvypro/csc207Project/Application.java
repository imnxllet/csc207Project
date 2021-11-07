package com.savvypro.csc207Project;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.ui.Login;
import com.savvypro.csc207Project.ui.StudentPortal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 *  Main application starting point
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory
            .getLogger(Application.class);

    @Autowired
    private Login login;

    @Autowired
    private StudentPortal studentPortal;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean userIsLoggedIn = false;
        Scanner scanner = new Scanner(System.in);
        Student loggedInStudent = null;

        while (true) {
            while (!userIsLoggedIn) {
                loggedInStudent = login.loginUser(scanner);
                userIsLoggedIn = loggedInStudent != null;
            }

            studentPortal.showStudentPortal(scanner, loggedInStudent.getUsername());
        }
    }
}
