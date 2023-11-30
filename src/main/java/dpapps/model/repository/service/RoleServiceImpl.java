package dpapps.model.repository.service;

import dpapps.exception.RoleNotFoundException;
import dpapps.model.Role;
import dpapps.model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;



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

    @Override
    public List<Role> getRolesById(List<Long> listOfRoles) {
        return this.roleRepository.findAllById(listOfRoles);
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        try {
           return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException());
        }
        catch (RoleNotFoundException e) {
            //TODO logger
        }
        return new Role();
    }

    @Override
    public Role getRoleById(Long id) {
        try {
            return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException());
        }
        catch (RoleNotFoundException e) {
            //TODO logger
        }
        return new Role();
    }


}
