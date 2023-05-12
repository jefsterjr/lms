package org.arcone.lmsapi.course.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateCourseDTO(@NotBlank String name) {


}
