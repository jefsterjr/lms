package org.arcone.lmsapi.couseenrollment.service.impl;

import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.course.model.entity.CourseStatus;
import org.arcone.lmsapi.course.model.repository.CourseRepository;
import org.arcone.lmsapi.course.service.CourseService;
import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentCreateDTO;
import org.arcone.lmsapi.couseenrollment.model.dto.CourseEnrollmentDTO;
import org.arcone.lmsapi.couseenrollment.model.entity.CourseEnrollment;
import org.arcone.lmsapi.couseenrollment.model.mapper.CourseEnrollmentMapper;
import org.arcone.lmsapi.couseenrollment.model.repository.CourseEnrollmentRepository;
import org.arcone.lmsapi.couseenrollment.service.CourseEnrollmentService;
import org.arcone.lmsapi.student.model.repositories.StudentRepository;
import org.arcone.lmsapi.util.exceptions.CourseFinishedException;
import org.arcone.lmsapi.util.exceptions.CourseNotFoundException;
import org.arcone.lmsapi.util.exceptions.CourseEnrollmentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    private static final Integer LIMIT_ENROLLED_COURSES = 3;

    private final CourseEnrollmentRepository CourseEnrollmentRepository;
    private final CourseService courseService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<CourseEnrollmentDTO> listStudentCourseEnrollment(Long studentId) {
        return CourseEnrollmentMapper.toDTO(CourseEnrollmentRepository.findAllByStudentId(studentId));
    }

    @Override
    public CourseEnrollmentDTO findCourseEnrollment(Long id) throws CourseEnrollmentNotFoundException {
        return CourseEnrollmentMapper.toDTO(CourseEnrollmentRepository.findById(id).orElseThrow(CourseEnrollmentNotFoundException::new));
    }

    @Override
    public void createCourseEnrollment(CourseEnrollmentCreateDTO dto) throws CourseNotFoundException, CourseFinishedException {
        if (courseService.isCourseFinished(dto.courseId())) {
            throw new CourseFinishedException();
        }

        CourseEnrollment CourseEnrollment = new CourseEnrollment();
        CourseEnrollment.setStudent(studentRepository.getReferenceById(dto.studentId()));
        CourseEnrollment.setCourse(courseRepository.getReferenceById(dto.courseId()));
        CourseEnrollmentRepository.save(CourseEnrollment);
    }

    @Override
    public void updateCourseEnrollmentStatus(Long id, CourseStatus courseStatus) throws CourseEnrollmentNotFoundException {
        CourseEnrollment existingCourseEnrollment = CourseEnrollmentRepository.findById(id).orElseThrow(CourseEnrollmentNotFoundException::new);
        CourseEnrollmentRepository.save(existingCourseEnrollment);
    }

    @Override
    public void deleteCourseEnrollment(Long id) throws CourseEnrollmentNotFoundException {
        CourseEnrollment CourseEnrollment = CourseEnrollmentRepository.findById(id).orElseThrow(CourseEnrollmentNotFoundException::new);
        if (!CollectionUtils.isEmpty(CourseEnrollment.getTaskLogs())) {
            CourseEnrollmentRepository.save(CourseEnrollment);
        } else {
            CourseEnrollmentRepository.deleteById(id);
        }
    }


}
