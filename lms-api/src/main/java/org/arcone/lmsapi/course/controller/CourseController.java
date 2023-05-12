package org.arcone.lmsapi.course.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.course.model.dto.CourseDTO;
import org.arcone.lmsapi.course.model.dto.CreateCourseDTO;
import org.arcone.lmsapi.course.service.CourseService;
import org.arcone.lmsapi.util.exceptions.CourseNotFoundException;
import org.arcone.lmsapi.util.exceptions.ExistingCourseException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Valid
public class CourseController {

    private final CourseService service;


    @GetMapping
    public List<CourseDTO> listCourses() {
        return service.listCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable Long id) throws CourseNotFoundException {
        return service.findCourse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void createCourse(@RequestBody @Valid CreateCourseDTO dto) throws ExistingCourseException {
        service.createCourse(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateCourse(@PathVariable Long id, @RequestBody CreateCourseDTO dto)
            throws CourseNotFoundException, ExistingCourseException {
        service.updateCourse(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
    }
}
