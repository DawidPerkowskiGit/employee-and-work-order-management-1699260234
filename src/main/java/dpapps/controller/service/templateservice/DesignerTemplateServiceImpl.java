package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;

@Service
public class DesignerTemplateServiceImpl implements DesignerTemplateService{
    @Override
    public String getDesignerPanelView() {
        return "designer/panel";
    }
}
