package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;

@Service
public class ErrorTemplateServiceImpl implements ErrorTemplateService{
    @Override
    public String getNotFoundView() {
        return "error/404";
    }
}
