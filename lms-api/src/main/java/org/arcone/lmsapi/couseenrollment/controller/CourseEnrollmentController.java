package org.arcone.lmsapi.couseenrollment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentCreateDTO;
import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentDTO;
import org.arcone.lmsapi.couseenrollment.service.CourseEnrollmentService;
import org.arcone.lmsapi.util.exceptions.CourseFinishedException;
import org.arcone.lmsapi.util.exceptions.CourseNotFoundException;
import org.arcone.lmsapi.util.exceptions.CourseEnrollmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrolled-courses")
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final CourseEnrollmentService CourseEnrollmentService;

    @GetMapping("/student/{studentId}")
    public List<CourseEnrollmentDTO> listStudentCourseEnrollments(@PathVariable Long studentId) {
        return CourseEnrollmentService.listStudentCourseEnrollment(studentId);
    }

    @GetMapping("/{id}")
    public CourseEnrollmentDTO findCourseEnrollment(@PathVariable Long id) throws CourseEnrollmentNotFoundException {
        return CourseEnrollmentService.findCourseEnrollment(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseEnrollment(@RequestBody @Valid CourseEnrollmentCreateDTO dto) throws CourseFinishedException, CourseNotFoundException {
        CourseEnrollmentService.createCourseEnrollment(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) throws CourseEnrollmentNotFoundException {
        CourseEnrollmentService.deleteCourseEnrollment(id);
    }
}
