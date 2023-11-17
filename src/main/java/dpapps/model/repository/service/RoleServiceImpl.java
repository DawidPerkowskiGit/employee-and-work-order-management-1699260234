package dpapps.model.repository.service;

import dpapps.constants.RoleConstants;
import dpapps.model.Role;
import dpapps.model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean roleExists(String role) {
        return this.roleRepository.existsByName(role);
    }

    @Override
    public void addRole(String roleName) {

//        if (RoleConstants.availableRoles.)

        Role role = new Role();
        role.setName(roleName);
        this.roleRepository.save(role);
    }

    @Override
    public String getName(Role role) {
        return role.getName();
    }


}
