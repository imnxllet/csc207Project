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
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerUnitTest {
    private static final String USERNAME = "username";
    private static final String OLD_FIRST_NAME = "Chris";
    private static final String OLD_LAST_NAME = "Lu";
    private static final String NEW_FIRST_NAME = "Andy";
    private static final String NEW_LAST_NAME = "Chu";

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Before
    public void setUp() {
        Mockito.when(studentService.getStudentByUsername(USERNAME))
                .thenReturn(Student.builder().username(USERNAME).firstName(OLD_FIRST_NAME).lastName(OLD_LAST_NAME).build());
    }

    @Test
    public void testUpdateStudent() {
        Student studentNewInfo = Student.builder().username(USERNAME).firstName(NEW_FIRST_NAME).lastName(NEW_LAST_NAME).build();
        Model model = new BindingAwareModelMap();

        studentController.updateStudent(studentNewInfo, model);

        assertEquals(NEW_FIRST_NAME, ((Student)model.getAttribute("student")).getFirstName());
        assertEquals(NEW_LAST_NAME, ((Student)model.getAttribute("student")).getLastName());
    }
}