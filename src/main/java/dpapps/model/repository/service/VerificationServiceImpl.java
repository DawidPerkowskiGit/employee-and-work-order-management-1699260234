package dpapps.model.repository.service;

import dpapps.exception.VerificationCodeNotFoundException;
import dpapps.model.User;
import dpapps.model.Verification;
import dpapps.model.repository.UserRepository;
import dpapps.model.repository.VerificationRepository;
import dpapps.tools.EmailService;
import dpapps.tools.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;

    private final EmailService emailService;


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
        this.verificationRepository.save(verification);
    }

    @Override
    public boolean verifyUser(Verification verification) {
        try {
            verification.getUser().setVerified(true);
            userRepository.save(verification.getUser());
            this.cleanupRepositoryAfterVerification(verification);
            return true;
        }
        catch (Exception e) {
            System.out.println("Could not verify user");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean processVerification(String code) {
       try {
           Verification verification = verificationRepository.findByCode(code);
           if (verification == null) {
               throw new VerificationCodeNotFoundException();
           }

           return this.verifyUser(verification);
       }
       catch (VerificationCodeNotFoundException e) {
           System.out.println("Verification code is invalid");
           e.printStackTrace();
       }
       catch (Exception e) {
           System.out.println("Could not verify user");
           e.printStackTrace();
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
