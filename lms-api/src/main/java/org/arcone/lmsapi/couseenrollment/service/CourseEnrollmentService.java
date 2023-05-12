package org.arcone.lmsapi.couseenrollment.service;

import org.arcone.lmsapi.course.model.entity.CourseStatus;
import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentCreateDTO;
import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentDTO;
import org.arcone.lmsapi.util.exceptions.CourseFinishedException;
import org.arcone.lmsapi.util.exceptions.CourseNotFoundException;
import org.arcone.lmsapi.util.exceptions.CourseEnrollmentNotFoundException;

import java.util.List;

public interface CourseEnrollmentService {
    List<CourseEnrollmentDTO> listStudentCourseEnrollment(Long studentId);

    CourseEnrollmentDTO findCourseEnrollment(Long id) throws CourseEnrollmentNotFoundException;

    void createCourseEnrollment(CourseEnrollmentCreateDTO dto) throws CourseNotFoundException, CourseFinishedException;

    void updateCourseEnrollmentStatus(Long id, CourseStatus courseStatus) throws CourseEnrollmentNotFoundException;

    void deleteCourseEnrollment(Long id) throws CourseEnrollmentNotFoundException;

}
