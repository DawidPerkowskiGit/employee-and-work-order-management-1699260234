package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class OperatorTemplateServiceImpl implements OperatorTemplateService{

    @Override
    public String getOperatorPanelView(Model model) {
        return "operator/panel";
    }

    @Override
    public String getNewTaskView(Model model) {
        return "operator/add-task";
    }

    @Override
    public String getSuccessfulTaskCreationView(RedirectAttributes redirectAttributes) {
        return "redirect:/operator/add-task?success";
    }

    @Override
    public String getUnsuccessfulTaskCreationView(RedirectAttributes redirectAttributes) {
        return "redirect:/operator/add-task?error";
    }

    @Override
    public String getTasksList(Model model) {
        return "tasks/tasks-list";
    }

    @Override
    public String getReviewView(Model model) {
        return "/operator/review";
    }

    @Override
    public String getArchived(Model model) {
        return "/tasks/archived";
    }

    @Override
    public String getReviewSubmitted() {
        return "redirect:/operator/archived?taskReviewed";
    }


    @Override
    public String getEditTaskView(Model model) {
        return "operator/edit-task";
    }

    @Override
    public String getSuccessfulTaskEditView(RedirectAttributes redirectAttributes, Long id) {
        return "redirect:/operator/tasks/edit/"+id+"?success";
    }

    @Override
    public String getUnsuccessfulTaskEditView(RedirectAttributes redirectAttributes, Long id) {
        return "redirect:/operator/tasks/edit/"+id+"?error";
    }
}
