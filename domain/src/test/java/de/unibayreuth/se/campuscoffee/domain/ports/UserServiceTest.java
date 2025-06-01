package de.unibayreuth.se.campuscoffee.domain.ports;

import de.unibayreuth.se.campuscoffee.domain.impl.UserServiceImpl;
import de.unibayreuth.se.campuscoffee.domain.model.User;
import de.unibayreuth.se.campuscoffee.domain.tests.TestFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDataService userDataService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void retrieveExpectedNumberOfUserEntries() {
        List<User> testFixtures = TestFixtures.getUserList();
        when(userDataService.getAll()).thenReturn(testFixtures);

        List<User> retrievedUser = userService.findAll();
        assertEquals(testFixtures.size(), retrievedUser.size());
    }

}
