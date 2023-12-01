package dpapps.model.repository.service;

import dpapps.exception.RoleNotFoundException;
import dpapps.model.Role;
import dpapps.model.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean roleExists(String role) {
        return this.roleRepository.existsByName(role);
    }

    @Override
    public boolean addRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);

        try {
            this.roleRepository.save(role);
            return true;
        }
        catch (Exception e) {
            logger.warn("Could not save new Role '" + roleName + "' in the database");
        }
        return false;
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
            logger.warn("Could not find Role with name '" + name + "' in the database.");
        }
        return new Role();
    }

    @Override
    public Role getRoleById(Long id) {
        try {
            return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException());
        }
        catch (RoleNotFoundException e) {
            logger.warn("Could not find Project with id '" + id + "' in the database.");
        }
        return new Role();
    }


}
