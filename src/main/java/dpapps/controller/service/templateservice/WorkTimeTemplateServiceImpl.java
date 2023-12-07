package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class WorkTimeTemplateServiceImpl implements WorkTimeTemplateService{
    @Override
    public String getPanel(Model model) {
        return "/work_time_tracking/panel";
    }
}
