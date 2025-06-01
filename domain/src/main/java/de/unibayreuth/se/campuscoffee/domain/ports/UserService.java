package de.unibayreuth.se.campuscoffee.domain.ports;

import de.unibayreuth.se.campuscoffee.domain.exceptions.UserNotFoundException;
import de.unibayreuth.se.campuscoffee.domain.model.User;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Interface for the implementation of the user service that the domain module provides as a port and implements itself.
 */
public interface UserService {
    void clear();
    // Define user service.
    @NonNull
    List<User> findAll();
    @NonNull
    User findById(Long id) throws UserNotFoundException;
    @NonNull
    User findByLogin(String loginName) throws UserNotFoundException;
    @NonNull
    User create(User user);
    @NonNull
    User update(Long id, User user) throws UserNotFoundException;
}
