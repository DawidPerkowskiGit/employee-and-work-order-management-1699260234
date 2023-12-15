package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class WorkTimeTemplateServiceImpl implements WorkTimeTemplateService{
    @Override
    public String getPanel(Model model) {
        return "/work_time_tracking/panel";
    }

    @Override
    public String getStartWork(RedirectAttributes redirectAttributes) {
        return "redirect:/worktime?startSuccess";
    }

    @Override
    public String getStopWork(RedirectAttributes redirectAttributes) {
        return "redirect:/worktime?stopSuccess";
    }

    @Override
    public String getStartBreak(RedirectAttributes redirectAttributes) {
        return "redirect:/worktime?breakStart";
    }

    @Override
    public String getStopBreak(RedirectAttributes redirectAttributes) {
        return "redirect:/worktime?breakStop";
    }

    @Override
    public String getWorkingLogs(Model model) {
        return "/work_time_tracking/logs";
    }
}
