package dpapps.controller.service;

import dpapps.controller.service.templateservice.DesignerTemplateService;
import org.springframework.stereotype.Service;

@Service
public class DesignerControllerServiceImpl implements DesignerControllerService{

    private final DesignerTemplateService templateService;

    public DesignerControllerServiceImpl(DesignerTemplateService templateService) {
        this.templateService = templateService;
    }

    @Override
    public String getPanel() {
        return templateService.getDesignerPanelView();
    }
}
