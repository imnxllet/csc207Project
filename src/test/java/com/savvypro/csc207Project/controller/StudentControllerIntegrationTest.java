package com.savvypro.csc207Project.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerIntegrationTest {
    private static final String USERNAME = "username";
    private static final String OLD_FIRST_NAME = "Chris";
    private static final String OLD_LAST_NAME = "Lu";
    private static final String NEW_FIRST_NAME = "Andy";
    private static final String NEW_LAST_NAME = "Chu";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Before
    public void setUp() {
        Mockito.when(studentService.getStudentByUsername(USERNAME))
                .thenReturn(Student.builder().username(USERNAME).firstName(OLD_FIRST_NAME).lastName(OLD_LAST_NAME).build());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        MvcResult response = mvc.perform(post("/student/update")
                .param("username", USERNAME)
                .param("firstName", NEW_FIRST_NAME)
                .param("lastName", NEW_LAST_NAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("studentInfoPage")).andReturn();

        assertTrue(response.getResponse().getContentAsString().contains(NEW_FIRST_NAME));
        assertTrue(response.getResponse().getContentAsString().contains(NEW_LAST_NAME));
    }
}