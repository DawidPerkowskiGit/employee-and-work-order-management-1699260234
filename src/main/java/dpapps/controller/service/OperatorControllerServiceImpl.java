package dpapps.controller.service;

import org.springframework.stereotype.Service;

@Service
public class OperatorControllerServiceImpl implements OperatorControllerService{
    @Override
    public String getPanel() {
        return "/operator/panel";
    }
}
