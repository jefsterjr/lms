package org.arcone.lmsapi.couseenrollment.model.dto;

import org.arcone.lmsapi.course.model.dto.CourseDTO;
import org.arcone.lmsapi.course.model.entity.CourseStatus;
import org.arcone.lmsapi.student.model.dto.StudentDTO;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogDTO;

import java.util.List;

public record CourseEnrollmentDTO(
        Long id,
        StudentDTO student,
        CourseDTO course,
        List<TaskLogDTO> taskLogs,
        CourseStatus status) {
}
