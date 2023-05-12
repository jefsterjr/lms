package org.arcone.lmsapi.taskcategory.model.repository;

import org.arcone.lmsapi.taskcategory.model.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

}
