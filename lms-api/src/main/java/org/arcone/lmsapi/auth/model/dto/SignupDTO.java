package org.arcone.lmsapi.auth.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record SignupDTO(@NotBlank(message = "First Name is required")
                        String firstName,

                        @NotBlank(message = "Last Name is required")
                        String lastName,

                        @NotNull(message = "Date of Birth is required")
                        @Past(message = "Invalid Date of Birth")
                        LocalDate dateOfBirth,

                        @NotBlank(message = "Email is required")
                        @Email(message = "Invalid email address.")
                        String email,

                        @NotBlank(message = "Phone Number is required")
                        String phoneNumber,

                        @NotBlank(message = "Address is required")
                        String address,

                        @NotBlank(message = "Password is required")
                        String password) {
}
