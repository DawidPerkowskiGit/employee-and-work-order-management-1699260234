package dpapps.user;

import dpapps.constants.UserMessagesConstants;
import dpapps.model.User;
import dpapps.model.repository.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDbTests {

    private final UserService userService;

    private final String[] userData = {"testlogin", "testpassword", "testemail"};

    private final User user = new User(userData[0], userData[1], userData[2]);

    @Autowired
    public UserDbTests(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    public void cleanUpBefore() {
        removeTestingUser();
    }

    @AfterEach
    public void cleanUpAfter() {
        removeTestingUser();
    }

    @Test
    public void shouldAddNewUser() {
        boolean userExistsBefore = userService.existsByLogin(userData[0]);
        userService.add(user);
        boolean userExistsAfter = userService.existsByLogin(userData[0]);

        assertThat(!userExistsBefore && userExistsAfter).isTrue();
    }

    @Test
    public void shouldRemoveExistingUser() {

        addTestingUser();

        boolean userExistsBefore = userService.existsByLogin(userData[0]);
        userService.delete(userData[0]);
        boolean userExistsAfter = userService.existsByLogin(userData[0]);

        assertThat(userExistsBefore && !userExistsAfter).isTrue();
    }

    @Test
    public void shouldNotAddNewUserWithExistingLogin() {
        userService.add(user);
        String message = userService.add(user);
        String expectedMessage = UserMessagesConstants.USER_ALREADY_EXISTS;
        assertEquals(message, expectedMessage);
    }

    @Test
    public void shouldNotRemoveNonExistingUser() {
        removeTestingUser();
        String message = userService.delete(userData[0]);
        String expectedMessage = UserMessagesConstants.USER_NOT_EXISTS;
        assertEquals(message, expectedMessage);
    }

    private void removeTestingUser() {
        userService.delete(userData[0]);
    }

    private void addTestingUser() {
        userService.add(user);
    }
}
