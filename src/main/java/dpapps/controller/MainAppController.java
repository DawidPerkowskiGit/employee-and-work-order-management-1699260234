package dpapps.controller;

import dpapps.controller.service.MainAppControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainAppController {

    private final MainAppControllerService controllerService;

    MainAppController(MainAppControllerService controllerService) {
        this.controllerService = controllerService;
    }

    /**
     * Applications health check endpoint
     */
    @GetMapping("/health")
    public @ResponseBody String healthCheck() {
        return controllerService.getHealthCheck();
    }

    /**
     * Index page endpoint
     */
    @GetMapping("/")
    public String homePageSlash() {
        return controllerService.getHomePage();
    }

    /**
     * Index page endpoint
     */
    @GetMapping("")
    public String homePage() {
        return controllerService.getHomePage();
    }

    @GetMapping("/expiredurl")
    public String expiredUrl() {
        return controllerService.expiredUrl();
    }

}
