package dpapps.controller.service;

import dpapps.model.User;
import org.springframework.ui.Model;

import java.util.List;

public interface AdminManagementService {
    String listUsers(Model model);
}
