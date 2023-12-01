package dpapps.model.repository.service;

import dpapps.constants.UserMessagesConstants;
import dpapps.exception.InvalidRoleNameException;
import dpapps.exception.UserNotFoundException;
import dpapps.model.Role;
import dpapps.model.User;
import dpapps.model.repository.UserRepository;
import dpapps.security.userregistration.UserDto;
import dpapps.tools.RoleChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final RoleService roleService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public String add(User user) {
        try {
            if (userRepository.existsByLogin(user.getLogin())) {
                logger.warn("Could not save User to the database. User with login '" + user.getLogin() + "' already exists");
                throw new DataIntegrityViolationException(UserMessagesConstants.USER_ALREADY_EXISTS);
            }
            userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            logger.warn("Could not save user to the database. User already exists");
            return UserMessagesConstants.USER_ALREADY_EXISTS;
        } catch (Exception exception) {
            logger.warn("Could not save user to the database.");
            return UserMessagesConstants.CANNOT_ADD_USER;
        }
        return UserMessagesConstants.USER_ADDED_SUCCESSFULLY;

    }

    @Override
    public String delete(String login) {
        try {
            if (!userRepository.existsByLogin(login)) {
                logger.warn("Could not remove User from the database. User with login '" + login + "'does not exists");
                throw new InvalidDataAccessApiUsageException(UserMessagesConstants.USER_NOT_EXISTS);
            }
            userRepository.deleteByLogin(login);
        } catch (InvalidDataAccessApiUsageException exception) {
            logger.warn("Could not remove User from the database. User does not exists");
            return UserMessagesConstants.USER_NOT_EXISTS;
        } catch (Exception exception) {
            logger.warn("Could not remove User from the database.");
            return UserMessagesConstants.CANNOT_DELETE_USER;
        }

        return UserMessagesConstants.USER_REMOVED_SUCCESSFULLY;

    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public User findByLogin(String login) throws UserNotFoundException {

        return userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User with login '" + login + "' could not be found"));

    }

    @Override
    public User findByLoginAndEmail(String login, String email) throws UserNotFoundException {
        return userRepository.findByLoginAndEmail(login, email).orElseThrow(() -> new UserNotFoundException("Could not find User with login '" + login + "' and email '" + email + "' in the database."));
    }

    public String add(UserDto userDto) {
        User user = new User(userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail(), userDto.getName());
        return this.add(user);
    }

    public boolean changePassword(String login, String password) {
        String newPassword = passwordEncoder.encode(password);

        try {
            User user = this.findByLogin(login);
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean grantUserRole(User user, Role role) throws InvalidRoleNameException {


        if (!this.isRoleValid(role)) {
            throw new InvalidRoleNameException("Role is invalid");
        }

        try {
            List<Role> userRoles = this.getUserRoles(user);
            userRoles.add(role);
            user.setRoles(userRoles);
            userRepository.save(user);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean grantUserRole(User user, String roleName) throws InvalidRoleNameException {

        if (!this.isRoleValid(roleName)) {
            throw new InvalidRoleNameException("Role is invalid");
        }

        try {
            Role role = this.roleService.getRoleByName(roleName);
            return this.grantUserRole(user, role);
        }
        catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return false;
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
        try {
            this.userRepository.save(user);
        }
        catch (Exception e) {
            logger.warn(e.getMessage());
        }
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
    public User getAuthenticatedUser() throws Exception {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = this.findByLogin(username);
            return user;
        }
        catch (Exception e) {
            logger.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<User> findAllByRole(String roleName) {
        Role role = this.roleService.getRoleByName(roleName);
        return this.userRepository.findAllByRoles(role);
    }

    @Override
    public List<User> findAllByRole(Long id) {
        Role role = this.roleService.getRoleById(id);
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
