package com.savvypro.csc207Project.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.repository.StudentRepository;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerUnitTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;


    @Before
    public void setUp() {
        Mockito.when(studentService.getStudentByUsername("chris"))
                .thenReturn(Student.builder().username("chris").build());
    }

    @Test
    public void testUpdateStudent() {
        Student studentNewInfo = Student.builder().username("chris").firstName("Chris").lastName("Lu").build();
        Model model = new BindingAwareModelMap();
        studentController.updateStudent(studentNewInfo, model);

        assertEquals("Chris", ((Student)model.getAttribute("student")).getFirstName());
    }
}