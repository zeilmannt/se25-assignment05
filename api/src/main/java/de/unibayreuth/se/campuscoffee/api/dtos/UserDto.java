package de.unibayreuth.se.campuscoffee.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for user metadata.
 *
 */
@Data
@Builder(toBuilder = true)
public class UserDto {
    private Long id; // id is null when creating a new task
    // Implement user DTO.

    @Pattern(regexp = "\\w+", message = "Login must contain only word characters")
    private String login;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    private String firstName;

    @Size(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    private String lastName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
