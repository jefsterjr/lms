package org.arcone.lmsapi.course.service;

import org.arcone.lmsapi.course.model.dto.CourseDTO;
import org.arcone.lmsapi.course.model.dto.CreateCourseDTO;
import org.arcone.lmsapi.util.exceptions.CourseNotFoundException;
import org.arcone.lmsapi.util.exceptions.ExistingCourseException;

import java.util.List;

public interface CourseService {

    List<CourseDTO> listCourses();

    CourseDTO findCourse(Long id) throws CourseNotFoundException;

    void createCourse(CreateCourseDTO course) throws ExistingCourseException;

    void updateCourse(Long id, CreateCourseDTO course) throws CourseNotFoundException, ExistingCourseException, ExistingCourseException;

    void deleteCourse(Long id);

    boolean isCourseFinished(Long id) throws CourseNotFoundException;
}
