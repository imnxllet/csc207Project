package com.savvypro.csc207Project.service.impl;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        Mockito.when(studentRepository.findById("chris"))
                .thenReturn(Optional.of(Student.builder().username("chris").build()));
    }

    @Test
    public void testGetStudentByUsername() {
        assertEquals("chris", studentService.getStudentByUsername("chris").getUsername());
    }
}