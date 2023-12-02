package dpapps.controller;

import dpapps.controller.service.DesignerControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DesignerController {

    private final DesignerControllerService controllerService;


    public DesignerController(DesignerControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/designer/panel")
    public String getPanel(Model model) {
        return controllerService.getPanel(model);
    }
}
