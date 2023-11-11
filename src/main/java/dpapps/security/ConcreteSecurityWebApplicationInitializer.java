package dpapps.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

/**
 * After configuring session management to allow set amount of session, HttpSessionEventPublisher has to be configured
 */
@Component
public class ConcreteSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    @Bean
    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }

}
