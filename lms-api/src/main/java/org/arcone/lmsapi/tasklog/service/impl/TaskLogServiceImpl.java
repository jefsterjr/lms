package org.arcone.lmsapi.tasklog.service.impl;

import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.couseenrollment.service.CourseEnrollmentService;
import org.arcone.lmsapi.taskcategory.model.mapper.TaskCategoryMapper;
import org.arcone.lmsapi.tasklog.model.mapper.TaskLogMapper;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogCreateDTO;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogDTO;
import org.arcone.lmsapi.tasklog.model.entity.TaskLog;
import org.arcone.lmsapi.tasklog.model.repository.TaskLogRepository;
import org.arcone.lmsapi.tasklog.service.TaskLogService;
import org.arcone.lmsapi.util.exceptions.TaskLogNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskLogServiceImpl implements TaskLogService {

    private final TaskLogRepository taskLogRepository;
    private final CourseEnrollmentService CourseEnrollmentService;

    @Override
    public List<TaskLogDTO> listTaskLogsByCourseEnrollment(Long CourseEnrollmentId) {
        return TaskLogMapper.toDTO(taskLogRepository.findAllByCourseEnrollmentId(CourseEnrollmentId));
    }

    @Override
    public TaskLogDTO findById(Long id) throws TaskLogNotFoundException {
        return TaskLogMapper.toDTO(taskLogRepository.findById(id)
                .orElseThrow(TaskLogNotFoundException::new));
    }

    @Override
    public void createTaskLog(TaskLogCreateDTO dto) {
        taskLogRepository.save(TaskLogMapper.toEntity(dto));
    }

    @Override
    public void updateTaskLog(Long id, TaskLogCreateDTO dto)
            throws TaskLogNotFoundException {
        TaskLog existingTaskLog = taskLogRepository.findById(id)
                .orElseThrow(TaskLogNotFoundException::new);

        existingTaskLog.setCategory(TaskCategoryMapper.toEntity(dto.category()));
        existingTaskLog.setTimeSpent(dto.timeSpent());

        taskLogRepository.save(existingTaskLog);
    }

    @Override
    public void deleteTaskLog(Long id) {
        taskLogRepository.deleteById(id);
    }
}
