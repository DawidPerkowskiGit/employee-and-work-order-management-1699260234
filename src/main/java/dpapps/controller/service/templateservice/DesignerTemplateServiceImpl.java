package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class DesignerTemplateServiceImpl implements DesignerTemplateService{
    @Override
    public String getDesignerPanelView(Model model) {
        return "designer/panel";
    }
}
