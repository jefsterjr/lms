package org.arcone.lmsapi.student.service;

import org.arcone.lmsapi.auth.model.dto.SignupDTO;
import org.arcone.lmsapi.student.model.dto.CreateStudentDTO;
import org.arcone.lmsapi.student.model.dto.StudentDTO;
import org.arcone.lmsapi.user.model.entity.User;
import org.arcone.lmsapi.util.exceptions.StudentNotFoundException;

public interface StudentService {

    StudentDTO findStudentByEmail(String email) throws StudentNotFoundException;

    void create(SignupDTO request, User user);

    StudentDTO findStudent(Long id) throws StudentNotFoundException;
}
