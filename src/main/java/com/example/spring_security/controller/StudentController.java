package com.example.spring_security.controller;

import com.example.spring_security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    List<Student> studentList = new ArrayList<>(
            Arrays.asList(
                    new Student(1,"Adam","Development"),
                    new Student(2,"Ben","Cloud")
            )
    );

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentList;
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        studentList.add(student);
    }

    @GetMapping("/csrf-token ")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
