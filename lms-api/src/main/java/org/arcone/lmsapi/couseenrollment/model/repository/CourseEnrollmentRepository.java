package org.arcone.lmsapi.couseenrollment.model.repository;

import org.arcone.lmsapi.couseenrollment.model.entity.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {

    List<CourseEnrollment> findAllByStudentId(Long studentId);

    Integer countAllByStudentId(Long studentId);
}
