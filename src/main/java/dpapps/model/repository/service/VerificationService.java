package dpapps.model.repository.service;

import dpapps.model.User;
import dpapps.model.Verification;

public interface VerificationService {

    /**
     * Generates verification code
     */
    String generateVerificationCode();

    /**
     * Adds verification code to the User Entity
     */
    void assignVerificationCodeToUser(User user, String code);

    /**
     * Verifies the user when all requirements are met (user exists, code is valid)
     */
    boolean verifyUser(Verification verification);

    /**
     * Attempts to verify the user while checking if the code is valid and user exists. Returns result of the whole process
     */
    boolean processVerification(String code);

    /**
     * Sends user email containing verification link
     */
    void sendVerificationEmail(User user, String code);
}
