package dpapps.user;

import dpapps.constants.UserMessagesConstants;
import dpapps.model.User;
import dpapps.model.repository.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class UserDbTests {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final String[] userData = {"testlogin", "testpassword", "testemail"};

    private final User user = new User(userData[0], userData[1], userData[2]);

    @Autowired
    public UserDbTests(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.user.setPassword(this.passwordEncoder.encode(user.getPassword()));
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
        this.addTestingUser();
        boolean userExistsAfter = userService.existsByLogin(userData[0]);

        assertThat(!userExistsBefore && userExistsAfter).isTrue();
    }

    @Test
    public void shouldRemoveExistingUser() {

        addTestingUser();

        boolean userExistsBefore = userService.existsByLogin(userData[0]);
        this.removeTestingUser();
        boolean userExistsAfter = userService.existsByLogin(userData[0]);

        assertThat(userExistsBefore && !userExistsAfter).isTrue();
    }

    @Test
    public void shouldNotAddNewUserWithExistingLogin() {
        this.addTestingUser();
        String message = this.addTestingUser();
        String expectedMessage = UserMessagesConstants.USER_ALREADY_EXISTS;
        assertEquals(message, expectedMessage);
    }

    @Test
    public void shouldNotRemoveNonExistingUser() {
        removeTestingUser();
        String message = this.removeTestingUser();
        String expectedMessage = UserMessagesConstants.USER_NOT_EXISTS;
        assertEquals(message, expectedMessage);
    }


    @Test
    public void shouldChangePassword() {
        addTestingUser();
        String oldPassword = userService.findByLogin(userData[0]).getPassword();

        userService.changePassword(userData[0], "new password");
        String newPassword = userService.findByLogin(userData[0]).getPassword();
        assertNotEquals(oldPassword, newPassword);
    }


    private String removeTestingUser() {
        return userService.delete(userData[0]);
    }

    private String addTestingUser() {
        return userService.add(user);
    }
}
