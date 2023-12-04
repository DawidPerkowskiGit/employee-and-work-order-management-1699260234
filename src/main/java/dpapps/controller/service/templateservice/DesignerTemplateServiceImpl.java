package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class DesignerTemplateServiceImpl implements DesignerTemplateService{
    @Override
    public String getDesignerPanelView(Model model) {
        return "designer/panel";
    }

    @Override
    public String getTasksList(Model model) {
        return "/tasks/tasks-list";
    }

    @Override
    public String getTaskDetails(Model model) {
        return "/tasks/details";
    }

    @Override
    public String getSuccessfulTaskCompletion(RedirectAttributes redirectAttributes, Long id) {
        return "redirect:/designer/tasks/details/"+id;
    }
}
