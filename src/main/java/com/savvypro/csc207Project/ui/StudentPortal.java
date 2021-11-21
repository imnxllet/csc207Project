package com.savvypro.csc207Project.ui;

import com.savvypro.csc207Project.controller.StudentController;
import com.savvypro.csc207Project.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Console UI to act as a student portal for user to perform activties such as
 * showing account info, updating account info
 */
@Component
public class StudentPortal {
    @Autowired
    private StudentController studentController;

    public void showStudentPortal(String username) {
        System.out.println("\n\n=== STUDENT PORTAL ===" +
                "\n1) Type 'profile' to show your student information" +
                "\n2) Type 'update' to update your info" +
                "\n3) Type 'exit' to exit the system" +
                "\n==================");

        printAskForCommandMessage();
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while (!command.equals("exit")) {
            if (command.equals("profile")) {
                studentController.displayStudent(username);
            } else if (command.equals("update")) {

                System.out.print("Enter first name and last name separated by comma (for example: chris,lu)\n Enter here => ");
                String[] name = scanner.nextLine().split(",");
                String firstName = name[0];
                String lastName = name[1];

                Student newStudentInfo = Student.builder().username(username).firstName(firstName).lastName(lastName).build();

                if (studentController.updateStudent(newStudentInfo)) {
                    System.out.println("Student info successfully updated!");
                } else {
                    System.out.println("Student info update failed!");
                }
            } else {
                System.out.println("No such command!");
            }

            printAskForCommandMessage();
            command = scanner.nextLine();
        }

        // command is not equal to 'exit'
        System.exit(1);
    }

    private void printAskForCommandMessage() {
        System.out.print(" Enter command (profile,update,exit)=> ");
    }
}
