package de.unibayreuth.se.campuscoffee.data.impl;

import de.unibayreuth.se.campuscoffee.data.mapper.UserEntityMapper;
import de.unibayreuth.se.campuscoffee.data.persistence.*;
import de.unibayreuth.se.campuscoffee.domain.exceptions.PosNotFoundException;
import de.unibayreuth.se.campuscoffee.domain.exceptions.UserNotFoundException;
import de.unibayreuth.se.campuscoffee.domain.model.User;
import de.unibayreuth.se.campuscoffee.domain.ports.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the POS data service that the domain layer provides as a port.
 */
@Service
@RequiredArgsConstructor
class UserDataServiceImpl implements UserDataService {
    private final UserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public void clear() {
        repository.deleteAllInBatch();
        repository.flush();
    }

    @Override
    @NonNull
    public List<User> getAll() {
        return repository.findAll().stream()
                .map(mapper::fromEntity)
                .toList();
    }

    @Override
    @NonNull
    public Optional<User> getById(@NonNull Long id) throws UserNotFoundException {
        return repository.findById(id)
                .map(mapper::fromEntity);
    }

    @Override
    @NonNull
    public Optional<User> getByLoginName(@NonNull String loginName) throws UserNotFoundException {
        return repository.findByLoginName(loginName)
                .map(mapper::fromEntity);
    }

    @Override
    @NonNull
    public User upsert(@NonNull User user) throws UserNotFoundException {
        if (user.getId() == null) {
            // create new user
            return mapper.fromEntity(repository.saveAndFlush(mapper.toEntity(user)));
        }

        // update existing user
        UserEntity userEntity = repository.findById(user.getId())
                .orElseThrow(() -> new PosNotFoundException("User with ID " + user.getId() + " does not exist."));
        // use mapper to be able to use the house number conversion logic
        UserEntity mappedUserEntity = mapper.toEntity(user);
        // update fields
        userEntity.setLoginName(mappedUserEntity.getLoginName());
        userEntity.setEmailAddress(mappedUserEntity.getEmailAddress());
        userEntity.setFirstName(mappedUserEntity.getFirstName());
        userEntity.setLastName(mappedUserEntity.getLastName());
        userEntity.setUpdatedAt(LocalDateTime.now());

        return mapper.fromEntity(repository.saveAndFlush(userEntity));

    }
}
