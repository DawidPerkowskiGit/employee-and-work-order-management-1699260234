package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class OperatorTemplateServiceImpl implements OperatorTemplateService{

    @Override
    public String getOperatorPanelView() {
        return "/operator/panel";
    }

    @Override
    public String getNewTaskView(Model model) {
        return "/operator/add-task";
    }

    @Override
    public String getSuccessfulTaskCreationView(RedirectAttributes redirectAttributes) {
        return "redirect:/operator/add-task?success";
    }

    @Override
    public String getUnsuccessfulTaskCreationView(RedirectAttributes redirectAttributes) {
        return "redirect:/operator/add-task?error";
    }
}
