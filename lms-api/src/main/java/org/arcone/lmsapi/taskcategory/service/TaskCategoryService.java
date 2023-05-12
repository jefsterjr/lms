package org.arcone.lmsapi.taskcategory.service;

import org.arcone.lmsapi.taskcategory.model.dto.TaskCategoryDTO;
import org.arcone.lmsapi.taskcategory.model.entity.TaskCategory;

import java.util.List;

public interface TaskCategoryService {

  List<TaskCategoryDTO> listTaskCategory();
}
