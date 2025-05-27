package de.unibayreuth.se.campuscoffee.api.dtos;

import lombok.Builder;
import lombok.Data;

/**
 * DTO for user metadata.
 *
 */
@Data
@Builder(toBuilder = true)
public class UserDto {
    private Long id; // id is null when creating a new task
    // TODO: Implement user DTO.
}
