package dpapps.model.repository.service;

import dpapps.constants.UserMessagesConstants;
import dpapps.exception.InvalidRoleNameException;
import dpapps.model.Role;
import dpapps.model.User;
import dpapps.model.repository.RoleRepository;
import dpapps.model.repository.UserRepository;
import dpapps.security.userregistration.UserDto;
import dpapps.tools.RoleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public String add(User user) {
        try {
            if (userRepository.existsByLogin(user.getLogin())) {
                throw new DataIntegrityViolationException(UserMessagesConstants.USER_ALREADY_EXISTS);
            }
            userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            return UserMessagesConstants.USER_ALREADY_EXISTS;
        } catch (Exception exception) {
            exception.printStackTrace();
            return UserMessagesConstants.CANNOT_ADD_USER;
        }
        return UserMessagesConstants.USER_ADDED_SUCCESSFULLY;

    }

    @Override
    public String delete(String login) {
        try {
            if (!userRepository.existsByLogin(login)) {
                throw new InvalidDataAccessApiUsageException(UserMessagesConstants.USER_NOT_EXISTS);
            }
            userRepository.deleteByLogin(login);
        } catch (InvalidDataAccessApiUsageException exception) {
            exception.printStackTrace();
            return UserMessagesConstants.USER_NOT_EXISTS;
        } catch (Exception exception) {
            exception.printStackTrace();
            return UserMessagesConstants.CANNOT_DELETE_USER;
        }

        return UserMessagesConstants.USER_REMOVED_SUCCESSFULLY;

    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByLoginAndEmail(String login, String email) {
        return userRepository.findByLoginAndEmail(login, email);
    }

    public String add(UserDto userDto) {
        User user = new User(userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail(), userDto.getName());
        return this.add(user);
    }

    public void changePassword(String login, String password) {
        String newPassword = passwordEncoder.encode(password);

        User user = this.findByLogin(login);

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public boolean grantUserRole(User user, Role role) {

        try {
            if (!this.isRoleValid(role)) {
                throw new InvalidRoleNameException();
            }
        } catch (InvalidRoleNameException e) {
            System.out.println("Provided invalid role name");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Could not check if specified role is valid");
            e.printStackTrace();
            return false;
        }

        try {
            List<Role> userRoles = this.getUserRoles(user);


            userRoles.add(role);
            user.setRoles(userRoles);
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Could not grant user this role");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean grantUserRole(User user, String roleName) {

        if (!this.isRoleValid(roleName)) {
            return false;
        }
        Role role = roleRepository.findByName(roleName);
        return this.grantUserRole(user, role);
    }

    @Override
    public List<Role> getUserRoles(User user) {
        return user.getRoles();
    }

    @Override
    public List<String> getUserRoleNames(User user) {
        List<String> roleNames = new ArrayList<>();

        for (Role role : this.getUserRoles(user)
        ) {
            roleNames.add(role.getName());
        }

        return roleNames;
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        if (this.userRepository.findById(id).isPresent()) {
            return this.userRepository.findById(id).get();
        }
        return new User();
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = this.findByLogin(username);


        return user;

    }

    @Override
    public List<User> findAllByRole(String roleName) {
        Role role = this.roleRepository.findByName(roleName);
        return this.userRepository.findAllByRoles(role);
    }

    @Override
    public List<User> findAllByRole(Long id) {
        Role role = this.roleRepository.findById(id).get();
        return this.userRepository.findAllByRoles(role);
    }

    private boolean isRoleValid(Role role) {
        return this.isRoleValid(role.getName());
    }

    private boolean isRoleValid(String roleName) {
        RoleChecker roleChecker = new RoleChecker();
        return roleChecker.isRoleValid(roleName);
    }
}
