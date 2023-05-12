package org.arcone.lmsapi.tasklog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogCreateDTO;
import org.arcone.lmsapi.tasklog.model.dto.TaskLogDTO;
import org.arcone.lmsapi.tasklog.service.TaskLogService;
import org.arcone.lmsapi.util.exceptions.CourseFinishedException;
import org.arcone.lmsapi.util.exceptions.CourseEnrollmentNotFoundException;
import org.arcone.lmsapi.util.exceptions.TaskLogNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task-logs")
@RequiredArgsConstructor
public class TaskLogController {

    private final TaskLogService taskLogService;

    @GetMapping("/enrolled-course/{CourseEnrollmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskLogDTO> listCourseEnrollmentTaskLogs(@PathVariable Long CourseEnrollmentId) {
        return taskLogService.listTaskLogsByCourseEnrollment(CourseEnrollmentId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskLogDTO findTaskLog(@PathVariable Long id) throws TaskLogNotFoundException {
        return taskLogService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTaskLog(@RequestBody @Valid TaskLogCreateDTO dto)
            throws CourseFinishedException, CourseEnrollmentNotFoundException {
        taskLogService.createTaskLog(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createTaskLog(@PathVariable Long id, @RequestBody TaskLogCreateDTO dto)
            throws CourseFinishedException, CourseEnrollmentNotFoundException, TaskLogNotFoundException {
        taskLogService.updateTaskLog(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Long id) {
        taskLogService.deleteTaskLog(id);
    }
}
