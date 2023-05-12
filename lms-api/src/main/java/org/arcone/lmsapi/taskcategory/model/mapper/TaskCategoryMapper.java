package org.arcone.lmsapi.taskcategory.model.mapper;

import org.arcone.lmsapi.taskcategory.model.dto.TaskCategoryDTO;
import org.arcone.lmsapi.taskcategory.model.entity.TaskCategory;

import java.util.List;

public class TaskCategoryMapper {


    private TaskCategoryMapper() {
    }

    public static TaskCategoryDTO toDTO(TaskCategory entity) {
        return new TaskCategoryDTO(entity.getId(), entity.getName());
    }

    public static TaskCategory toEntity(TaskCategoryDTO category) {
        return new TaskCategory(category.getId(), category.getName());
    }

    public static List<TaskCategoryDTO> toDTO(List<TaskCategory> entities) {
        return entities.stream().map(TaskCategoryMapper::toDTO).toList();
    }

}
