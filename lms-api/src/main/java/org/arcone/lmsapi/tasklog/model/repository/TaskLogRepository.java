package org.arcone.lmsapi.tasklog.model.repository;

import org.arcone.lmsapi.tasklog.model.entity.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskLogRepository extends JpaRepository<TaskLog, Long> {

    List<TaskLog> findAllByCourseEnrollmentId(Long CourseEnrollmentId);
}
