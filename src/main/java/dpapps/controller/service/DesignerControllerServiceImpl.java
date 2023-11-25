package dpapps.controller.service;

import org.springframework.stereotype.Service;

@Service
public class DesignerControllerServiceImpl implements DesignerControllerService{

    @Override
    public String getPanel() {
        return "/designer/panel";
    }
}
