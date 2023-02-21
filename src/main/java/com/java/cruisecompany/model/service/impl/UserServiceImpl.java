package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.UserDAO;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.PasswordHashUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoUser;
import static com.java.cruisecompany.model.utils.MapperDTO.mapUserToDTO;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void create(UserDTO userDTO) throws ServiceException {
        try {
            userDAO.create(mapDTOtoUser(userDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void register(UserDTO userDTO, String password) throws ServiceException {
        User user = mapDTOtoUser(userDTO);
        user.setPassword(PasswordHashUtil.hash(password));

        try {
            userDAO.create(user);
        } catch (DAOException e) {
            checkAlreadyExist(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(UserDTO userDTO) throws ServiceException {
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
        UserDTO userDTO;
        try {
            userDTO = mapUserToDTO(userDAO.findById(id).orElseThrow(NoSuchUserException::new));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(userDTO);
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
        UserDTO userDTO;
        try {
            User user = userDAO.findByLogin(login).orElseThrow(NoSuchUserException::new);

            if (!PasswordHashUtil.verify(user.getPassword(), password)) {
                throw new NoSuchUserException();
            }

            userDTO = mapUserToDTO(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(userDTO);
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
        UserDTO userDTO;
        try {
            userDTO = mapUserToDTO(userDAO.findByLogin(login).orElseThrow(NoSuchUserException::new));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(userDTO);
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
        if (message != null && message.contains("Duplicate entry")) {
            if (message.contains("login")) {
                throw new InvalidInputException("error.user.loginExists", e);
            } else if (message.contains("email")) {
                throw new InvalidInputException("error.user.emailExists", e);
            }
        }
    }
}
