package dpapps.controller;

import dpapps.constants.MessageConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainAppController {

    @GetMapping("/health")
    public @ResponseBody String healthCheck() {
        return MessageConstants.APP_IS_RUNNING;
    }
}
