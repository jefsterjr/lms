package org.arcone.lmsapi.taskcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.taskcategory.model.dto.TaskCategoryDTO;
import org.arcone.lmsapi.taskcategory.model.entity.TaskCategory;
import org.arcone.lmsapi.taskcategory.model.mapper.TaskCategoryMapper;
import org.arcone.lmsapi.taskcategory.model.repository.TaskCategoryRepository;
import org.arcone.lmsapi.taskcategory.service.TaskCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCategoryServiceImpl implements TaskCategoryService {

  private final TaskCategoryRepository taskCategoryRepository;

  @Override
  public List<TaskCategoryDTO> listTaskCategory() {
    return TaskCategoryMapper.toDTO(taskCategoryRepository.findAll());
  }
}
