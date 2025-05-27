package de.unibayreuth.se.campuscoffee.domain.model;

import lombok.*;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Domain class that stores the POS metadata.
 */
@Data
public class Pos implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private Long id; // null when the POS has not been created yet
        private LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("UTC")); // set on POS creation
        private LocalDateTime updatedAt = createdAt; // set on POS creation and update
        @NonNull
        private String name;
        @NonNull
        private String description;
        @NonNull
        private PosType type;
        @NonNull
        private CampusType campus;
        @NonNull
        private String street;
        @NonNull
        private String houseNumber;
        @NonNull
        private Integer postalCode;
        @NonNull
        private String city;
}
