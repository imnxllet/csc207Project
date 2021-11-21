package com.savvypro.csc207Project.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
    private static final String USERNAME = "username";
    private static final String OLD_FIRST_NAME = "Chris";
    private static final String OLD_LAST_NAME = "Lu";
    private static final String NEW_FIRST_NAME = "Andy";
    private static final String NEW_LAST_NAME = "Chu";

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private Student studentInDb = Student.builder().username(USERNAME)
            .firstName(OLD_FIRST_NAME).lastName(OLD_LAST_NAME).build();

    private Student newStudentInfo = Student.builder().username(USERNAME)
            .firstName(NEW_FIRST_NAME).lastName(NEW_LAST_NAME).build();

    @Before
    public void setUp() {
        Mockito.when(studentService.getStudentByUsername(USERNAME))
                .thenReturn(studentInDb);
    }

    @Test
    public void testDisplayStudent() {
        studentController.displayStudent(USERNAME);

        // check if studentService is being called once when studentController.displayStudent was called
        Mockito.verify(studentService, times(1)).getStudentByUsername(USERNAME);

    }

    @Test
    public void testUpdateStudent() {
        assertTrue(studentController.updateStudent(newStudentInfo));

        Mockito.verify(studentService, times(1)).getStudentByUsername(USERNAME);

        assertEquals(NEW_FIRST_NAME, studentInDb.getFirstName());
        assertEquals(NEW_LAST_NAME, studentInDb.getLastName());
    }
}