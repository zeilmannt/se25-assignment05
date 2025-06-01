package de.unibayreuth.se.campuscoffee.domain.model;

import lombok.Data;

import javax.annotation.Nullable;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

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
    private Instant createdAt; // set by the system on creation

    @Nullable
    private Instant updatedAt; // set by the system on update

    private String loginName;
    private String email;
    private String firstName;
    private String lastName;
}
