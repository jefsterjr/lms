package org.arcone.lmsapi.course.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.course.model.dto.CourseDTO;
import org.arcone.lmsapi.course.model.dto.CreateCourseDTO;
import org.arcone.lmsapi.course.model.entity.Course;
import org.arcone.lmsapi.course.model.mapper.CourseMapper;
import org.arcone.lmsapi.course.model.repository.CourseRepository;
import org.arcone.lmsapi.course.service.CourseService;
import org.arcone.lmsapi.util.exceptions.CourseNotFoundException;
import org.arcone.lmsapi.util.exceptions.ExistingCourseException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseDTO> listCourses() {
        return CourseMapper.toDTO(courseRepository.findAll());
    }

    @Override
    public CourseDTO findCourse(Long id) throws CourseNotFoundException {
        return CourseMapper.toDTO(getCourse(id));
    }

    @Override
    public void createCourse(CreateCourseDTO dto) throws ExistingCourseException {
        checkCourseName(dto.name());
        Course course = new Course(null, dto.name(), LocalDate.now(), LocalDate.now().plus(6, ChronoUnit.MONTHS));
        courseRepository.save(course);
    }

    private void checkCourseName(String course) throws ExistingCourseException {
        if (courseRepository.existsByName(course)) {
            throw new ExistingCourseException();
        }
    }

    @Override
    @Transactional
    public void updateCourse(Long id, CreateCourseDTO dto) throws CourseNotFoundException, ExistingCourseException {
        checkCourseName(dto.name());
        Course course = getCourse(id);
        course.setName(dto.name());
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean isCourseFinished(Long id) throws CourseNotFoundException {
        Course course = getCourse(id);
        return course.getEndDate().isBefore(LocalDate.now());
    }

    private Course getCourse(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }
}
