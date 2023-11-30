package dpapps.tools;

import dpapps.constants.EmailServiceConstants;
import dpapps.variables.EmailServiceVars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    public boolean sendSimpleMessage(String to, String subject, String text) {
        if (EmailServiceVars.CURRENT_EMAILS_SENT_COUNT < EmailServiceConstants.DAILY_EMAIL_SENT_LIMIT) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("easyworkmanager@gmail.com");
                message.setTo(to);
                message.setSubject(subject);
                message.setText(text);
                emailSender.send(message);
                EmailServiceVars.CURRENT_EMAILS_SENT_COUNT++;
                return true;
            }
            catch (MailException e) {
                System.out.println("Could not send an email");
                e.printStackTrace();
            }
            catch (Exception e) {
                System.out.println("Could not perform an action during sending email procedure");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Reached daily maximum count of emails sent. Cannot send more emails");
        }
        return false;
    }
}