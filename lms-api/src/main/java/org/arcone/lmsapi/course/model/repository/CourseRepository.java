package org.arcone.lmsapi.course.model.repository;

import org.arcone.lmsapi.course.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByName(String courseName);

    boolean existsByNameAndIdNot(String courseName, Long id);
}
