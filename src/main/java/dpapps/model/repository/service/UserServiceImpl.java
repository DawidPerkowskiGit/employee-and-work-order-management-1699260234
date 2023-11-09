package dpapps.model.repository.service;

import dpapps.constants.UserMessagesConstants;
import dpapps.model.User;
import dpapps.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}