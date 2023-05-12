package org.arcone.lmsapi.auth.model.dto;

public record JWTResponseDTO(String token, Long id, String username, String email, String role) {
}
