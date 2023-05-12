package org.arcone.lmsapi.student.service.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arcone.lmsapi.auth.model.dto.SignupDTO;
import org.arcone.lmsapi.student.model.dto.StudentDTO;
import org.arcone.lmsapi.student.model.entities.Student;
import org.arcone.lmsapi.student.model.mapper.StudentMapper;
import org.arcone.lmsapi.student.model.repositories.StudentRepository;
import org.arcone.lmsapi.student.service.StudentService;
import org.arcone.lmsapi.user.model.entity.User;
import org.arcone.lmsapi.util.exceptions.StudentException;
import org.arcone.lmsapi.util.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public void create(SignupDTO request, User user) {
        try {
            checkAge(request.dateOfBirth());
            Student student = StudentMapper.getEntity(request);
            student.setUser(user);
            repository.save(student);
        } catch (StudentException e) {
            log.warn("Error on creating a Student", e);
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public StudentDTO findStudent(Long id) throws StudentNotFoundException {
        return StudentMapper.getDTO(repository.findById(id).orElseThrow(StudentNotFoundException::new));
    }

    @Override
    public StudentDTO findStudentByEmail(String email) throws StudentNotFoundException {
        return StudentMapper.getDTO(repository.findByEmail(email).orElseThrow(StudentNotFoundException::new));
    }


    private void checkAge(@NotNull LocalDate dateOfBirth) {
        if (dateOfBirth.isAfter(LocalDate.now().minus(16, ChronoUnit.YEARS)))
            throw new StudentException("Student shall be a minimum of 16 years old");
    }
}
