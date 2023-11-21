package dpapps.controller.service;

import dpapps.model.User;
import dpapps.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AdminManagementServiceImpl implements AdminManagementService{

    private final UserRepository userRepository;

    @Autowired
    public AdminManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/dashboard";
    }
}
