package de.unibayreuth.se.campuscoffee.domain.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Domain class that stores the user metadata.
 */
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id; // null when the user has not been created yet
    // TODO: Implement user domain class. Hint: the @Nullable annotations are important for the Lombok @Data annotation (see https://projectlombok.org/features/Data).
}
