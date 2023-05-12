package org.arcone.lmsapi.course.model.dto;

import java.time.LocalDate;

public record CourseDTO(String name, LocalDate startDate, LocalDate endDate) {
}
