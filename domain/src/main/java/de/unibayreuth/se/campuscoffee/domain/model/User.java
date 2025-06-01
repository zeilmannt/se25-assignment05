package de.unibayreuth.se.campuscoffee.domain.model;

import lombok.Data;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Domain class that stores the user metadata.
 */
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Nullable
    private Long id; // null when the user has not been created yet
    // Implement user domain class. Hint: the @Nullable annotations are important for the Lombok @Data annotation (see https://projectlombok.org/features/Data).

    @Nullable
    private LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("UTC")); // set on USER creation

    @Nullable
    private LocalDateTime updatedAt = createdAt;

    @NonNull
    private String loginName;
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}
