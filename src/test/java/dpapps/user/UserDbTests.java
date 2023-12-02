package dpapps.user;

import dpapps.constants.UserMessagesConstants;
import dpapps.exception.UserNotFoundException;
import dpapps.model.User;
import dpapps.model.Verification;
import dpapps.model.repository.VerificationRepository;
import dpapps.model.repository.service.UserService;
import dpapps.model.repository.service.VerificationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDbTests {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final VerificationService verificationService;

    private final VerificationRepository verificationRepository;

    private final String[] userData = {"testlogin", "testpassword", "testemail"};

    private final User user = new User(userData[0], userData[1], userData[2]);


    @Autowired
    public UserDbTests(UserService userService, PasswordEncoder passwordEncoder, VerificationService verificationService, VerificationRepository verificationRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.verificationService = verificationService;
        this.verificationRepository = verificationRepository;
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
        addTestingUser();
        boolean userExistsAfter = userService.existsByLogin(userData[0]);

        assertThat(!userExistsBefore && userExistsAfter).isTrue();
    }

    @Test
    public void shouldRemoveExistingUser() {

        addTestingUser();

        boolean userExistsBefore = userService.existsByLogin(userData[0]);
        removeTestingUser();
        boolean userExistsAfter = userService.existsByLogin(userData[0]);

        assertThat(userExistsBefore && !userExistsAfter).isTrue();
    }

    @Test
    public void shouldNotAddNewUserWithExistingLogin() {
        addTestingUser();
        String message = addTestingUser();
        String expectedMessage = UserMessagesConstants.USER_ALREADY_EXISTS;
        assertEquals(message, expectedMessage);
    }

    @Test
    public void shouldNotRemoveNonExistingUser() {
        removeTestingUser();
        String message = removeTestingUser();
        String expectedMessage = UserMessagesConstants.USER_NOT_EXISTS;
        assertEquals(message, expectedMessage);
    }


    @Test
    public void shouldChangePassword() throws UserNotFoundException {
        addTestingUser();
        String oldPassword = userService.findByLogin(userData[0]).getPassword();

        userService.changePassword(userData[0], "new password");
        String newPassword = userService.findByLogin(userData[0]).getPassword();
        assertNotEquals(oldPassword, newPassword);
    }

    @Test
    public void shouldVerifyUser() throws UserNotFoundException {
        cleanupVerificationRepository();
        addTestingUser();

        User user = userService.findByLogin(userData[0]);

        boolean verifiedBefore = user.getVerified();

        setupVerification(userData[0]);
        Verification verification = verificationRepository.findByUser(user).get();
        String code = verification.getCode();

        boolean result = verificationService.processVerification(code);

        boolean verifiedAfter = userService.findByLogin(userData[0]).getVerified();

        assertTrue(result == verifiedAfter == !verifiedBefore);
    }

    @Test
    public void shouldNotVerifyUser() throws UserNotFoundException {
        cleanupVerificationRepository();
        addTestingUser();

        User user = userService.findByLogin(userData[0]);

        boolean verifiedBefore = user.getVerified();

        setupVerification(userData[0]);
        Verification verification = verificationRepository.findByUser(user).get();
        String code = verification.getCode() + "invalid";

        boolean result = verificationService.processVerification(code);

        boolean verifiedAfter = userService.findByLogin(userData[0]).getVerified();

        assertFalse(result == verifiedBefore == verifiedAfter);
    }

    private String removeTestingUser() {
        try {

            return userService.delete(userData[0]);
        } catch (Exception e) {
            System.out.println("could not remove testing user");
            e.printStackTrace();
        }
        return "";
    }

    private String addTestingUser() {
        return userService.add(user);
    }

    private void cleanupVerificationRepository() {

        try {
            User user = userService.findByLogin(userData[0]);
            Verification verification = verificationRepository.findByUser(user).get();
            verificationRepository.deleteByCode(verification.getCode());
        } catch (Exception e) {
            System.out.println("Verification repository cleanup failed");
            e.printStackTrace();
        }
    }

    private void setupVerification(String login) throws UserNotFoundException {
        User user = userService.findByLogin(login);
        String verificationCode = verificationService.generateVerificationCode();
        verificationService.assignVerificationCodeToUser(user, verificationCode);
    }
}
