package org.arcone.lmsapi.student.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.student.model.dto.StudentDTO;
import org.arcone.lmsapi.student.service.StudentService;
import org.arcone.lmsapi.util.exceptions.StudentNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/email/{email}")
    @Valid
    public StudentDTO findStudentByEmail(@PathVariable @Email String email) throws StudentNotFoundException {
        return studentService.findStudentByEmail(email);
    }

}
