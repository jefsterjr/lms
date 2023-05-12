package org.arcone.lmsapi.course.model.mapper;

import org.arcone.lmsapi.course.model.dto.CourseDTO;
import org.arcone.lmsapi.course.model.entity.Course;

import java.util.List;

public class CourseMapper {

    public static List<CourseDTO> toDTO(List<Course> entities) {
        return entities.stream().map(CourseMapper::toDTO).toList();
    }

    public static CourseDTO toDTO(Course entity) {
        return new CourseDTO(entity.getName(), entity.getStartDate(),entity.getEndDate());
    }

    public static Course toEntity(CourseDTO course) {
        return new Course(null, course.name(), null, null);
    }
}
