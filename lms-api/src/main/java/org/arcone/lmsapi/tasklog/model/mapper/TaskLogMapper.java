package org.arcone.lmsapi.tasklog.model.mapper;

import org.arcone.lmsapi.couseenrollment.model.mapper.CourseEnrollmentMapper;
import org.arcone.lmsapi.taskcategory.model.mapper.TaskCategoryMapper;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogCreateDTO;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogDTO;
import org.arcone.lmsapi.tasklog.model.entity.TaskLog;

import java.util.List;

public class TaskLogMapper {

    private TaskLogMapper() {
    }

    public static List<TaskLogDTO> toDTO(List<TaskLog> entities) {
        return entities.stream().map(TaskLogMapper::toDTO).toList();
    }

    public static TaskLogDTO toDTO(TaskLog entity) {
        return new TaskLogDTO(entity.getId(), entity.getDate(),
                CourseEnrollmentMapper.toDTO(entity.getCourseEnrollment()),
                TaskCategoryMapper.toDTO(entity.getCategory()), entity.getDescription(), entity.getTimeSpent());
    }

    public static TaskLog toEntity(TaskLogCreateDTO dto) {
        return new TaskLog(null, dto.date(),
                CourseEnrollmentMapper.toEntity(dto.CourseEnrollment()),
                TaskCategoryMapper.toEntity(dto.category()), dto.description(), dto.timeSpent());
    }

    public static TaskLog toEntity(TaskLogDTO dto) {
        return new TaskLog(null, dto.date(),
                CourseEnrollmentMapper.toEntity(dto.CourseEnrollment()),
                TaskCategoryMapper.toEntity(dto.category()), dto.description(), dto.timeSpent());
    }

    public static List<TaskLog> toEntity(List<TaskLogDTO> taskLogs) {
        return taskLogs.stream().map(TaskLogMapper::toEntity).toList();
    }
}
