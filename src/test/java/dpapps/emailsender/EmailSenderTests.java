package dpapps.emailsender;

import dpapps.tools.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailSenderTests {

    private final EmailService emailService;

    private final String emailTo = System.getenv("EMAIL_ADDRESS");

    private final String emailSubject = "Test message";

    private final String emailText = "Test content of email sending service";


    @Autowired
    public EmailSenderTests(EmailService emailService) {
        this.emailService = emailService;
    }

    @Test
    public void shouldCorrectlySendEmail() {
        boolean result = emailService.sendSimpleMessage(emailTo, emailSubject, emailText);
        assertTrue(result);
    }
}
