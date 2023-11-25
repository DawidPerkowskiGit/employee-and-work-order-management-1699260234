package dpapps.controller;

import dpapps.controller.service.OperatorControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OperatorController {

    private final OperatorControllerService controllerService;

    @Autowired
    public OperatorController(OperatorControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/operator/panel")
    public String renerPanel() {
        return this.controllerService.getPanel();
    }

}
