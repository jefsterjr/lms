package org.arcone.lmsapi.tasklog.model.dto;


import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentDTO;
import org.arcone.lmsapi.taskcategory.model.dto.TaskCategoryDTO;

import java.time.LocalDate;

public record TaskLogCreateDTO(
        LocalDate date,
        CourseEnrollmentDTO CourseEnrollment,
        TaskCategoryDTO category,
        String description,
        Double timeSpent) {
}
