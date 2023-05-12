package org.arcone.lmsapi.student.model.mapper;

import org.arcone.lmsapi.auth.model.dto.SignupDTO;
import org.arcone.lmsapi.student.model.dto.StudentDTO;
import org.arcone.lmsapi.student.model.entities.Student;

public class StudentMapper {

    public static Student getEntity(StudentDTO dto) {
        return new Student(null, dto.firstName(), dto.lastName(), dto.dateOfBirth(), dto.address(), dto.phoneNumber(), dto.email(), null);
    }

    public static Student getEntity(SignupDTO dto) {
        return new Student(null, dto.firstName(), dto.lastName(), dto.dateOfBirth(), dto.address(), dto.phoneNumber(), dto.email(), null);
    }

    public static StudentDTO getDTO(Student entity) {
        return new StudentDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getDateOfBirth(), entity.getAddress(), entity.getPhoneNumber(), entity.getEmail());
    }

    private StudentMapper() {
    }
}
