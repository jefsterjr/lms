package org.arcone.lmsapi.couseenrollment.model.mapper;

import org.arcone.lmsapi.course.model.mapper.CourseMapper;
import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentDTO;
import org.arcone.lmsapi.couseenrollment.model.entity.CourseEnrollment;
import org.arcone.lmsapi.student.model.mapper.StudentMapper;
import org.arcone.lmsapi.tasklog.model.mapper.TaskLogMapper;

import java.util.List;

public class CourseEnrollmentMapper {


    public static CourseEnrollmentDTO toDTO(CourseEnrollment entity) {
        return new CourseEnrollmentDTO(entity.getId(),
                StudentMapper.getDTO(entity.getStudent()),
                CourseMapper.toDTO(entity.getCourse()), TaskLogMapper.toDTO(entity.getTaskLogs()), entity.getStatus());
    }

    public static CourseEnrollment toEntity(CourseEnrollmentDTO dto) {
        return new CourseEnrollment(null, StudentMapper.getEntity(dto.student()), CourseMapper.toEntity(dto.course()), TaskLogMapper.toEntity(dto.taskLogs()), dto.status());
    }


    public static List<CourseEnrollmentDTO> toDTO(List<CourseEnrollment> entities) {
        return entities.stream().map(CourseEnrollmentMapper::toDTO).toList();
    }

}
