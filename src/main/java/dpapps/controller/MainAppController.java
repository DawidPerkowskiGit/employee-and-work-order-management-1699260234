package dpapps.controller;

import dpapps.constants.MessageConstants;
import dpapps.service.MainAppControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainAppController {

    private final MainAppControllerService controller;

    @Autowired
    MainAppController(MainAppControllerService controller) {
        this.controller = controller;
    }

    /**
     * Applications health check endpoint
     */
    @GetMapping("/health")
    public @ResponseBody String healthCheck() {
        return controller.getHealthCheck();
    }

    /**
     * Index page endpoint
     */
    @GetMapping("/")
    public String homePageSlash() {
        return controller.getHomePage();
    }

    /**
     * Index page endpoint
     */
    @GetMapping("")
    public String homePage() {
        return controller.getHomePage();
    }




}
