package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.UserDAO;
import com.java.cruisecompany.model.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoUser;
import static com.java.cruisecompany.model.utils.MapperDTO.mapUserToDTO;
import static com.java.cruisecompany.model.utils.ValidationUtil.*;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void create(UserDTO userDTO) throws ServiceException {
        User user = mapDTOtoUser(userDTO);
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    //check for existing user with the same login/username
    @Override
    public void register(UserDTO userDTO, String password, String confirmPassword) throws ServiceException {
        validateUser(userDTO);
        validatePassword(password, "error.user.password");
        confirmPassword(password, confirmPassword, "error.user.confirmPassword");

        User user = mapDTOtoUser(userDTO);
        user.setPassword(password);
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            checkAlreadyExist(e);
        }
    }

    @Override
    public void update(UserDTO userDTO) throws ServiceException {
        validateUser(userDTO);

        User user = mapDTOtoUser(userDTO);
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserDTO> findById(long id) throws ServiceException {
        Optional<UserDTO> userDTO;
        try {
            Optional<User> user = userDAO.findById(id);
            userDTO = Optional.of(mapUserToDTO(user.orElseThrow(NoSuchUserException::new)));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() throws ServiceException {
        List<UserDTO> userDTOs = new ArrayList<>();
        try {
            List<User> users = userDAO.findAll();
            users.forEach(user -> userDTOs.add(mapUserToDTO(user)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOs;
    }

    @Override
    public Optional<UserDTO> findByLoginAndPass(String login, String password) throws ServiceException {
        Optional<UserDTO> userDTO;
        try {
            Optional<User> user = userDAO.findByLoginAndPass(login, password);
            userDTO = Optional.of(mapUserToDTO(user.orElseThrow(NoSuchUserException::new)));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }

    @Override
    public void topUpBalance(long userId, BigDecimal amount) throws ServiceException {
        try {
            userDAO.topUpBalance(userId, amount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> findByInitials(String initials) throws ServiceException {
        List<UserDTO> userDTOs = new ArrayList<>();
        try {
            List<User> users = userDAO.findByInitials(initials);
            users.forEach(user -> userDTOs.add(mapUserToDTO(user)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOs;
    }

    @Override
    public Optional<UserDTO> findByLogin(String login) throws ServiceException {
        Optional<UserDTO> userDTO;
        try {
            Optional<User> user = userDAO.findByLogin(login);
            userDTO = Optional.of(mapUserToDTO(user.orElseThrow(NoSuchUserException::new)));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> findSorted(String query) throws ServiceException {
        List<UserDTO> userDTOs = new ArrayList<>();
        try {
            List<User> users = userDAO.findSorted(query);
            users.forEach(user -> userDTOs.add(mapUserToDTO(user)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTOs;
    }

    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return userDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private static void checkAlreadyExist(DAOException e) throws InvalidInputException {
        String message = e.getMessage();
        if (message.contains("Duplicate entry")) {
            if (message.contains("login")) {
                throw new InvalidInputException("error.user.loginExists", e);
            } else if (message.contains("email")) {
                throw new InvalidInputException("error.user.emailExists", e);
            }
        }
    }

    private static void validateUser(UserDTO userDTO) throws InvalidInputException {
        validateOnlyLetters(userDTO.getLogin(), "error.user.login");
        validateEmail(userDTO.getEmail(), "error.user.email");
        validateOnlyLetters(userDTO.getFirstName(), "error.user.firstName");
        validateOnlyLetters(userDTO.getLastName(), "error.user.lastName");
    }
}
