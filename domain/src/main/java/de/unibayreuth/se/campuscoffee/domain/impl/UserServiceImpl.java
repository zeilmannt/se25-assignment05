package de.unibayreuth.se.campuscoffee.domain.impl;

import de.unibayreuth.se.campuscoffee.domain.exceptions.UserNotFoundException;
import de.unibayreuth.se.campuscoffee.domain.model.User;
import de.unibayreuth.se.campuscoffee.domain.ports.UserDataService;
import de.unibayreuth.se.campuscoffee.domain.ports.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDataService userDataService;

    @Override
    public void clear() {
        userDataService.clear();
    }

    @Override
    @NonNull
    public List<User> findAll() {
        return userDataService.getAll();
    }

    @Override
    @NonNull
    public User findById(Long id) throws UserNotFoundException {
        return verifyUserExists(id);
    }

    @Override
    @NonNull
    public User findByLogin(String loginName) throws UserNotFoundException {
        return verifyUserExists(loginName);
    }

    @Override
    @NonNull
    public User create(User user) {
        return userDataService.upsert(user);
    }

    @Override
    @NonNull
    public User update(Long id, User user) throws UserNotFoundException {
        if(userDataService.getById(id).isEmpty()){
            throw new UserNotFoundException("USER with id \"" + id + "\" does not exist.");
        }
        else{
            user.setId(id);
            return userDataService.upsert(user);
        }
    }

    private User verifyUserExists(@NonNull String loginName) throws UserNotFoundException{
        return userDataService.getByLoginName(loginName)
                .orElseThrow(() -> new UserNotFoundException("USER with name \"" + loginName + "\" does not exist."));
    }

    private User verifyUserExists(@NonNull Long id) throws UserNotFoundException{
        return userDataService.getById(id)
                .orElseThrow(() -> new UserNotFoundException("USER with id \"" + id + "\" does not exist."));
    }
}
