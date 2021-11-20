package com.savvypro.csc207Project.controller;

import com.savvypro.csc207Project.entity.Student;
import com.savvypro.csc207Project.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService service;

    @Test
    public void testUpdateStudent()
            throws Exception {

        Student student = new Student();
        student.setUsername("Chris");
        student.setPassword("password");
        student.setFirstName("Chris");
        student.setLastName("Lu");

        when(service.getStudentByUsername("Chris")).thenReturn(student);

        MvcResult response = mvc.perform(post("/student/update")
                .param("username", "Chris")
                .param("firstName", "Chris")
                .param("lastName", "Lu"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("studentInfoPage")).andReturn();

        assertTrue("Chris", response.getResponse().getContentAsString().contains("Chris"));
    }
}