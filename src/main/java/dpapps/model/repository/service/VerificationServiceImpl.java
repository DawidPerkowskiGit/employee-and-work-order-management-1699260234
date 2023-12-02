package dpapps.model.repository.service;

import dpapps.exception.VerificationCodeNotFoundException;
import dpapps.model.User;
import dpapps.model.Verification;
import dpapps.model.repository.UserRepository;
import dpapps.model.repository.VerificationRepository;
import dpapps.tools.EmailService;
import dpapps.tools.VerificationCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public VerificationServiceImpl(VerificationRepository verificationRepository,
                                   UserRepository userRepository, EmailService emailService) {
        this.verificationRepository = verificationRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public String generateVerificationCode() {
        VerificationCodeGenerator generator = new VerificationCodeGenerator();
        return generator.generateCode();
    }

    @Override
    public void assignVerificationCodeToUser(User user, String code) {
        Verification verification = new Verification(user, code);
        verificationRepository.save(verification);
    }

    @Override
    public boolean verifyUser(Verification verification) {
        try {
            verification.getUser().setVerified(true);
            userRepository.save(verification.getUser());
            cleanupRepositoryAfterVerification(verification);
            return true;
        }
        catch (Exception e) {
            logger.warn("Could not verify the User");
        }
        return false;
    }

    @Override
    public boolean processVerification(String code) {
       try {
           Verification verificationToken = verificationRepository.findByCode(code).orElseThrow(() -> new VerificationCodeNotFoundException());

           return verifyUser(verificationToken);
       }
       catch (VerificationCodeNotFoundException e) {
           logger.warn("Verification code is invalid");
       }
       catch (Exception e) {
           logger.warn("Could not verify the User");
       }
       return false;
    }

    private void cleanupRepositoryAfterVerification(Verification verification) {
        verificationRepository.deleteByCode(verification.getCode());
    }

    public void sendVerificationEmail(User user, String code) {
        String message = "Welcome to Easy Work Manager.\n\nTo begin using the application you have to verify your account. Click the link below to verify your account\n";
        String link = "https://employeeandworkmanagement.onrender.com/verify/" + code;
        String subject = "Easy Work Manager account verification";
        message = message + link;
        emailService.sendSimpleMessage(user.getEmail(), subject, message);
    }

}
