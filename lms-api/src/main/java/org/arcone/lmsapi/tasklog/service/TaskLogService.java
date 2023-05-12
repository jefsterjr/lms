package org.arcone.lmsapi.tasklog.service;


import jakarta.validation.Valid;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogCreateDTO;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogDTO;
import org.arcone.lmsapi.util.exceptions.CourseFinishedException;
import org.arcone.lmsapi.util.exceptions.CourseEnrollmentNotFoundException;
import org.arcone.lmsapi.util.exceptions.TaskLogNotFoundException;

import java.util.List;

public interface TaskLogService {

    List<TaskLogDTO> listTaskLogsByCourseEnrollment(Long CourseEnrollmentId);

    TaskLogDTO findById(Long id) throws TaskLogNotFoundException;

    void createTaskLog(@Valid TaskLogCreateDTO taskLog) throws CourseFinishedException, CourseEnrollmentNotFoundException;

    void updateTaskLog(Long id, TaskLogCreateDTO taskLog)
            throws TaskLogNotFoundException, CourseFinishedException, CourseEnrollmentNotFoundException;

    void deleteTaskLog(Long id);
}
