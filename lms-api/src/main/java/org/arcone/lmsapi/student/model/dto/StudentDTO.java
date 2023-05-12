package org.arcone.lmsapi.student.model.dto;

import java.time.LocalDate;

public record StudentDTO(
        Long id,

        String firstName,

        String lastName,

        LocalDate dateOfBirth,

        String email,

        String phoneNumber,

        String address) {
}
